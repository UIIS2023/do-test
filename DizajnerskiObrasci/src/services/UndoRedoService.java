package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import commands.Command;

public class UndoRedoService {
	
	private Stack<Command> toUndo = new Stack<Command>();
	private List<Command> toRedo = new ArrayList<Command>();
	
	public void undo() {
		Command cmd = toUndo.pop();
		cmd.unexec();
		toRedo.add(0, cmd);
	}
	
	public void redo() {
		Command cmd = toRedo.get(0);
		cmd.exec();
		toUndo.push(cmd);
		toRedo.remove(0);
	}
	
	public void saveExecutedCommand(Command command) {
		toUndo.push(command);
		toRedo.clear();
	}
	
	public void clear() {
		toUndo.clear();
		toRedo.clear();
	}

	public int getNumberInUndoList() {
		return toUndo.size();
	}
	public int getNumberInRedoList() {
		return toRedo.size();
	}

}