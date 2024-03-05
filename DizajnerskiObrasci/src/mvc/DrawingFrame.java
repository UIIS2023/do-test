package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import drawing.FrmDrawing;
import drawing.PnlDrawing;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import observer.Observer;
import observer.TransferObject;

import javax.swing.JTable;
import javax.swing.JTextArea;

public class DrawingFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel whiteBoardForDrawing = new JPanel();
	private DrawingController ctrl = null;
	
	private final ButtonGroup btnGroup = new ButtonGroup();
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	private final JButton btnEdgeColor = new JButton("Edge");
	private final JButton btnInnerColor = new JButton("Inner");
	private final JPanel pnlWest = new JPanel();
	private final JButton btnMoveOneup = new JButton("One up");
	private final JButton btnNewButton = new JButton("Top");
	private final JButton btnOnedown = new JButton("One down");
	private final JButton btnDownmax = new JButton("Bottom");
	private final JButton btnUndo = new JButton("UNDO");
	private final JButton btnRedo = new JButton("REDO");
	private final JButton btnSaveFIle = new JButton("Save file");
	private final JButton btnDelete;
	private final JButton btnModify;
	
	private final JTextArea logTextArea;
	private final JButton btnSaveLog = new JButton("Save log");
	private final JButton btnReadFile = new JButton("Read file");
	private final JButton btnReadLog = new JButton("Read log");
	private final JButton btnNextLog = new JButton("Next log");

	public DrawingFrame(DrawingModel model) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setTitle("Drawing App - Rankov Dragan IT1/2019");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		whiteBoardForDrawing = new View(model);
		whiteBoardForDrawing.setBackground(Color.WHITE);
		contentPane.add(whiteBoardForDrawing, BorderLayout.CENTER);
		whiteBoardForDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tglbtnSelect.isSelected()) {
					ctrl.handleSelect(e);
				} else 
				ctrl.handleMouseClick(e, btnEdgeColor.getForeground(), btnInnerColor.getForeground());
			}
		});

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(SystemColor.inactiveCaption);
		contentPane.add(pnlNorth, BorderLayout.NORTH);

		pnlNorth.add(tglbtnPoint);
		pnlNorth.add(tglbtnLine);
		pnlNorth.add(tglbtnRectangle);
		pnlNorth.add(tglbtnCircle);
		pnlNorth.add(tglbtnDonut);
		pnlNorth.add(tglbtnHexagon);
		

		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnHexagon);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setLayout(new BorderLayout());

		JPanel pnlSouthTop = new JPanel();
		pnlSouthTop.setBackground(SystemColor.inactiveCaption);
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.add(pnlSouthTop, BorderLayout.NORTH);
		btnGroup.add(tglbtnSelect);

		pnlSouthTop.add(tglbtnSelect);

		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modify();
			}
		});
		pnlSouthTop.add(btnModify);
		btnGroup.add(btnModify);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		pnlSouthTop.add(btnDelete);
		btnGroup.add(btnDelete);
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseEdgeColor();
			}
		});
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.handleUndo();
			}
		});
		btnUndo.setBackground(new Color(0, 128, 128));
		
		pnlSouthTop.add(btnUndo);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.handleRedo();
			}
		});
		btnRedo.setBackground(new Color(0, 128, 128));
		
		pnlSouthTop.add(btnRedo);
		
		pnlSouthTop.add(btnEdgeColor);
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseInnerColor();
			}
		});
		btnInnerColor.setForeground(new Color(255, 255, 255));
		
		pnlSouthTop.add(btnInnerColor);
		
		logTextArea = new JTextArea();
		logTextArea.setRows(6);
        logTextArea.setEditable(false);        

        JScrollPane scrollPane = new JScrollPane(logTextArea);
        pnlSouth.add(scrollPane, BorderLayout.SOUTH);
        
        pnlWest.setBackground(SystemColor.inactiveCaption);
        pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
        contentPane.add(pnlWest, BorderLayout.WEST);
        btnMoveOneup.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ctrl.moveOneUp();
        	}
        });
        
        pnlWest.add(btnMoveOneup);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ctrl.moveUpTop();
        	}
        });
        
        pnlWest.add(btnNewButton);
        btnOnedown.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ctrl.moveOneDown();
        	}
        });
        
        pnlWest.add(btnOnedown);
        btnDownmax.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ctrl.moveDownMax();
        	}
        });
        
        pnlWest.add(btnDownmax);
        btnSaveFIle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser jFileChooser = new JFileChooser(new File("C:\\"));
        		
        		int result = jFileChooser.showSaveDialog(null);
        		if(result == JFileChooser.APPROVE_OPTION) {
        			String path = jFileChooser.getSelectedFile().getAbsolutePath();
        			try {
						ctrl.saveFile(path);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Saving failed.", "Failed!", JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
        		}
        	}
        });
        
        pnlWest.add(btnSaveFIle);
        btnSaveLog.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser jFileChooser = new JFileChooser(new File("C:\\"));
        		
        		int result = jFileChooser.showSaveDialog(null);
        		if(result == JFileChooser.APPROVE_OPTION) {
        			String path = jFileChooser.getSelectedFile().getAbsolutePath();
        			try {
						ctrl.saveLog(path);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Saving failed.", "Failed!", JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
        		}
        	}
        });
        
        pnlWest.add(btnSaveLog);
        btnReadFile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ctrl.readFile();
        	}
        });
        
        pnlWest.add(btnReadFile);
        btnReadLog.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser jFileChooser = new JFileChooser(new File("C:\\"));
        		jFileChooser.setDialogTitle("Read log");
        		int result = jFileChooser.showOpenDialog(null);
        		if (result == JFileChooser.APPROVE_OPTION) {
        			try {
						ctrl.readLog(jFileChooser.getSelectedFile().getAbsolutePath());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null,"Problem with file read","Error",JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
        		}
        	}
        });
        
        pnlWest.add(btnReadLog);
        btnNextLog.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ctrl.runNextLog();
        	}
        });
        
        pnlWest.add(btnNextLog);

		whiteBoardForDrawing.repaint();
		
		tglbtnHexagon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tglbtnHexagon.isSelected()) {
                    tglbtnSelect.setSelected(false);
                    tglbtnPoint.setSelected(false);
                    tglbtnLine.setSelected(false);
                    tglbtnRectangle.setSelected(false);
                    tglbtnCircle.setSelected(false);
                    tglbtnDonut.setSelected(false);
                }
            }
        });
	}

	protected void chooseEdgeColor() {
		Color color = JColorChooser.showDialog(null, "Choose a color", btnEdgeColor.getBackground());
		btnEdgeColor.setForeground(color == null ? Color.BLACK : color);
	}
	
	protected void chooseInnerColor() {
		Color color = JColorChooser.showDialog(null, "Choose a color", btnInnerColor.getBackground());
		btnInnerColor.setForeground(color == null ? Color.WHITE : color);
	}
	
	protected void modify() {
		ctrl.modify();
	}


	

	protected void delete() {
		if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected shape?", "Yes",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
		{
			ctrl.delete();
		}
	}
	
	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}
	
	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}
	
	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public void setCtrl(DrawingController ctrl) {
		this.ctrl = ctrl;
	}
	
	public void addLog(String log) {
		logTextArea.append(log + "\n");
	}
	
	public String getLogs() {
		return logTextArea.getText();
	}

	public void clearLogs() {
		logTextArea.setText("");
	}

	@Override
	public void update(TransferObject object) {
		btnDownmax.setEnabled(object.moveDownMaxButton);
		btnOnedown.setEnabled(object.moveDownButton);
		btnMoveOneup.setEnabled(object.moveUpButton);
		btnNewButton.setEnabled(object.moveUpMaxButton);
		btnSaveFIle.setEnabled(object.saveFileButton);
		btnSaveLog.setEnabled(object.saveLogButton);
		btnUndo.setEnabled(object.undoButton);
		btnRedo.setEnabled(object.redoButton);
		btnDelete.setEnabled(object.deleteButton);
		btnModify.setEnabled(object.modifyButton);
		btnNextLog.setEnabled(object.nextLogButton);
	}
	
}
