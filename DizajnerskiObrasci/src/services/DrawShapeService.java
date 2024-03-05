package services;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;

import commands.CmdDraw;
import commands.Command;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgRectangle;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class DrawShapeService {

	private Point startPoint;
	
	public CmdDraw draw(DrawingFrame frame, DrawingModel model, MouseEvent me, Color edge, Color inner) {
		Shape newShape = null;
		Point click = new Point(me.getX(), me.getY());
		CmdDraw command = null;
		if (frame.getTglbtnPoint().isSelected()) {

			newShape = new Point(click.getX(), click.getY(), false, edge);
			command = new CmdDraw(newShape, model);
		} else if (frame.getTglbtnLine().isSelected()) {

			if (startPoint == null)
				startPoint = click;
			else {
				newShape = new Line(startPoint, new Point(me.getX(), me.getY()), false, edge);
				command = new CmdDraw(newShape, model);
				startPoint = null;
			}

		} else if (frame.getTglbtnCircle().isSelected()) {

			DlgCircle dialog = new DlgCircle();

			dialog.getTxtX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtY().setEditable(false);
			dialog.setInnerColor(inner);
			dialog.setOutlineColor(edge);
			dialog.setVisible(true);

			if (dialog.isOk()) {
				newShape = dialog.getCircle();
				command = new CmdDraw(newShape, model);
			}

		} else if (frame.getTglbtnDonut().isSelected()) {

			DlgDonut dialog = new DlgDonut();
			dialog.setModal(true);
			dialog.getTxtX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.setInnerColor(inner);
			dialog.setOutlineColor(edge);
			dialog.getTxtY().setEditable(false);
			dialog.setVisible(true);

			if (dialog.isOk()) {

				newShape = dialog.getDonut();
				command = new CmdDraw(newShape, model);
			}
		} else if (frame.getTglbtnRectangle().isSelected()) {

			
			DlgRectangle dialog = new DlgRectangle();
			dialog.setModal(true);
			dialog.getTxtX().setText("" + Integer.toString(me.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(me.getY()));
			dialog.getTxtY().setEditable(false);
			dialog.setOutlineColor(edge);
			dialog.setInnerColor(inner);
			dialog.setVisible(true);

			if (!dialog.isOk())
				return null;

			try {
				newShape = dialog.getRect();
				command = new CmdDraw(newShape, model);
			} catch (Exception ex) {

				JOptionPane.showMessageDialog(frame, "Wrong data type", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnHexagon().isSelected()) {
	        DlgHexagon dialog = new DlgHexagon();
	        dialog.setModal(true);
	        dialog.getTxtX().setText(Integer.toString(click.getX()));
	        dialog.getTxtY().setText(Integer.toString(click.getY()));
	        dialog.setColor(edge);
	        dialog.setInnerColor(inner);
	        dialog.setVisible(true);
	
	        if (dialog.isOk()) {
	            int x = Integer.parseInt(dialog.getTxtX().getText());
	            int y = Integer.parseInt(dialog.getTxtY().getText());
	            newShape = new HexagonAdapter(new Point(x, y), Integer.parseInt(dialog.getTxtR().getText()), false, dialog.getColor(), dialog.getInnerColor());
	            command = new CmdDraw(newShape, model);
	        }

		}

		if (command != null)
			command.exec();
	
		frame.repaint();
		return command;
	
	}
}
