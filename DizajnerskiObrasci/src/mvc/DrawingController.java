package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import commands.CmdDelete;
import commands.CmdDraw;
import commands.CmdMoveDownMax;
import commands.CmdMoveOneDown;
import commands.CmdMoveOneUp;
import commands.CmdMoveUpMax;
import commands.Command;
import geometry.Shape;
import observer.Observable;
import observer.Observer;
import observer.TransferObject;
import services.DeletingService;
import services.DrawShapeService;
import services.FIleService;
import services.LoggingService;
import services.ModificationService;
import services.MoveZAxisService;
import services.ReadLogService;
import services.SelectService;
import services.UndoRedoService;
import strategy.SaveFileStrategy;
import strategy.SaveLogStrategy;
import strategy.SaveManager;

public class DrawingController implements Observable {
	
	private DrawingModel model = null;
	private DrawingFrame frame = null;
	private List<Observer> observers = new ArrayList<Observer>();
	private List<String> logsFromFile = new ArrayList<String>();
	
	private DrawShapeService drawShapeService = new DrawShapeService();
	private SelectService selectService = new SelectService();
	private MoveZAxisService zAxisService = new MoveZAxisService();
	private UndoRedoService undoRedoService = new UndoRedoService();
	private SaveManager saveManager = new SaveManager();
	private ModificationService modificationService = new ModificationService();
	private LoggingService loggingService = new LoggingService();
	private DeletingService deletingService = new DeletingService();
	private FIleService fileService = new FIleService();
	private ReadLogService readLogService = new ReadLogService();
	
	public DrawingController(DrawingModel model) {
		this.model = model;
	}
	
	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}

	public void handleMouseClick(MouseEvent e, Color edge, Color inner) {
		CmdDraw cmd = drawShapeService.draw(frame, model, e, edge, inner);
		if (cmd!=null) {
			undoRedoService.saveExecutedCommand(cmd);
			String commandText = loggingService.logAdd(cmd.getShape());
			frame.addLog(commandText);
		}
		notifyObservers();
	}
	
	public void handleSelect(MouseEvent e) {
		Command cmd = selectService.handleSelection(e, model);
		if (cmd != null) {
			cmd.exec();
			frame.repaint();
			undoRedoService.saveExecutedCommand(cmd);
			String text = loggingService.logSelectOrDeselect(model, cmd);
			frame.addLog(text);
		}
		notifyObservers();
	}

	public void moveOneUp() {
		Command command = zAxisService.moveOneUp(model);
		frame.repaint();
		undoRedoService.saveExecutedCommand(command);
		String s=loggingService.logMoveOneUp(model, (CmdMoveOneUp)command);
		frame.addLog(s);
		notifyObservers();
	}

	public void moveUpTop() {
		Command cmd  =zAxisService.moveUpMax(model);
		frame.repaint();
		undoRedoService.saveExecutedCommand(cmd);
		String s=loggingService.logMoveUpMax(model, (CmdMoveUpMax)cmd);
		frame.addLog(s);
		notifyObservers();
	}

	public void moveOneDown() {
		Command cmd=zAxisService.moveOneDown(model);
		frame.repaint();
		undoRedoService.saveExecutedCommand(cmd);
		String s=loggingService.logMoveOneDown(model, (CmdMoveOneDown)cmd);
		frame.addLog(s);
		notifyObservers();
	}

	public void moveDownMax() {
		Command cmd = zAxisService.moveDownMax(model);
		frame.repaint();
		undoRedoService.saveExecutedCommand(cmd);
		String s=loggingService.logMoveDownMax(model, (CmdMoveDownMax)cmd);
		frame.addLog(s);
		notifyObservers();
	}

	public void handleUndo() {
		undoRedoService.undo();
		frame.addLog("undo");
		frame.repaint();
		notifyObservers();
	}
	
	public void handleRedo() {
		undoRedoService.redo();
		frame.addLog("redo");
		frame.repaint();
		notifyObservers();
	}
	
	public void saveFile(String filePath) throws IOException {
		saveManager.setStrategy(new SaveFileStrategy(model));
		saveManager.save(filePath);
		frame.repaint();
		notifyObservers();
	}

	public void modify() {
		Command cmd = modificationService.modifySelected(model);
		frame.repaint();
		if (cmd!=null) {
			String s =loggingService.logModify(cmd);
			frame.addLog(s);
			undoRedoService.saveExecutedCommand(cmd);
		}
		notifyObservers();
	}
	
	public void delete() {
		CmdDelete cmd = deletingService.delete(model);
		String s =loggingService.logDelete(cmd);
		frame.addLog(s);
		undoRedoService.saveExecutedCommand(cmd);
		frame.repaint();
		notifyObservers();
	}

	public void saveLog(String filePath) throws IOException {
		saveManager.setStrategy(new SaveLogStrategy(frame.getLogs()));
		saveManager.save(filePath);
		frame.repaint();
		notifyObservers();
	}
	
	public void readFile() {
		List<Shape> list = fileService.readFile();
		if(list!=null && list.size() > 0) {
			model.getList().clear();
			for(Shape s:list) {
				model.getList().add(s);
			}
		}
		
		frame.clearLogs();
		undoRedoService.clear();
		frame.repaint();
		notifyObservers();
	}
	
	public void readLog(String filePath) throws IOException {
		logsFromFile = readLogService.readLogFile(filePath);
		model.getList().clear();
		undoRedoService.clear();
		frame.repaint();
		frame.clearLogs();
		notifyObservers();
	}

	public void runNextLog() {
		String log = logsFromFile.get(0);
		logsFromFile.remove(0);
		if (log.equals("undo")) {
			handleUndo();
		} else if (log.equals("redo")) {
			handleRedo();
		} else {
			Command cmd = readLogService.getCommand(log, model);
			if (cmd != null) {
				cmd.exec();
				frame.addLog(log);
				undoRedoService.saveExecutedCommand(cmd);
			}
		}
		frame.repaint();
		notifyObservers();
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
		
	}

	@Override
	public void notifyObservers() {
		TransferObject object = new TransferObject();
		object.modifyButton = model.getListSelected().size() == 1;
		object.deleteButton = model.getListSelected().size() >= 1;
		
		object.undoButton = undoRedoService.getNumberInUndoList() > 0;
		object.redoButton = undoRedoService.getNumberInRedoList() > 0;
		object.saveFileButton = model.getList().size() > 0;
		object.saveLogButton = frame.getLogs().trim().length() > 0;
		if (model.getListSelected().size() == 1) {
			Shape s =model.getListSelected().get(0);
			object.moveDownButton = model.getList().indexOf(s) > 0;
			object.moveDownMaxButton = model.getList().indexOf(s) > 0;
			object.moveUpButton = model.getList().indexOf(s) < model.getList().size()-1;
			object.moveUpMaxButton = model.getList().indexOf(s) < model.getList().size() -1 ;
		} else {
			object.moveDownButton = false;
			object.moveDownMaxButton = false;
			object.moveUpButton = false;
			object.moveUpMaxButton = false;
		}
		object.nextLogButton = logsFromFile.size() > 0;
		for (Observer ob:observers) {
			ob.update(object);
		}
		
	}
	
}
