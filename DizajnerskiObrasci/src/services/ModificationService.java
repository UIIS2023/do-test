package services;

import commands.CmdModifyCircle;
import commands.CmdModifyDonut;
import commands.CmdModifyHexagon;
import commands.CmdModifyLine;
import commands.CmdModifyPoint;
import commands.CmdModifyRectangle;
import commands.Command;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingModel;

public class ModificationService {

	public Command modifySelected(DrawingModel model) {
		Shape selectedShape = model.getListSelected().get(0);
		if (selectedShape != null) {

			if (selectedShape instanceof Point) {

				Point p = (Point) selectedShape;
				DlgPoint dialog = new DlgPoint();

				dialog.getTxtX().setText("" + Integer.toString(p.getX()));
				dialog.getTxtY().setText("" + Integer.toString(p.getY()));
				dialog.getBtnColor().setBackground(p.getColor());
				dialog.setVisible(true);

				if (dialog.isOk()) {
					CmdModifyPoint command = new CmdModifyPoint((Point)selectedShape, dialog.getPoint());
					command.exec();
					return command;
				}

			} else if (selectedShape instanceof Donut) {

				Donut donut = (Donut) selectedShape;
				DlgDonut dialogd = new DlgDonut();

				dialogd.getTxtX().setText("" + Integer.toString(donut.getCenter().getX()));
				dialogd.getTxtY().setText("" + Integer.toString(donut.getCenter().getY()));
				dialogd.getTxtRadius().setText("" + Integer.toString(donut.getRadius()));
				dialogd.getTxtInnerRadius().setText("" + Integer.toString(donut.getInnerRadius()));
				dialogd.getBtnInnerColor().setBackground(donut.getInnerColor());
				dialogd.getBtnOutlineColor().setBackground(donut.getColor());
				dialogd.setModal(true);
				dialogd.setVisible(true);

				if (dialogd.isOk()) {
					CmdModifyDonut command = new CmdModifyDonut((Donut)selectedShape, dialogd.getDonut());
					command.exec();
					return command;
				}
			} else if (selectedShape instanceof Circle && (selectedShape instanceof Donut) == false) {

				Circle circle = (Circle) selectedShape;
				DlgCircle dialog = new DlgCircle();

				dialog.getTxtX().setText("" + Integer.toString(circle.getCenter().getX()));
				dialog.getTxtY().setText("" + Integer.toString(circle.getCenter().getY()));
				dialog.getTxtRadius().setText("" + Integer.toString(circle.getRadius()));
				dialog.getBtnInnerColor().setBackground(circle.getInnerColor());
				dialog.getBtnOutlineColor().setBackground(circle.getColor());

				dialog.setVisible(true);
				dialog.setModal(true);

				if (dialog.isOk()) {
					CmdModifyCircle command = new CmdModifyCircle((Circle)selectedShape, dialog.getCircle());
					command.exec();
					return command;
				}

			} else if (selectedShape instanceof Line) {

				Line line = (Line) selectedShape;
				DlgLine dialog = new DlgLine();

				dialog.getTxtXStartP().setText("" + Integer.toString(line.getStartPoint().getX()));
				dialog.getTxtYStartP().setText("" + Integer.toString(line.getStartPoint().getY()));
				dialog.getTxtXEndP().setText("" + Integer.toString(line.getEndPoint().getX()));
				dialog.getTxtYEndP().setText("" + Integer.toString(line.getEndPoint().getY()));
				dialog.getBtnColor().setBackground(line.getColor());

				dialog.setVisible(true);

				if (dialog.isOk()) {
					CmdModifyLine command = new CmdModifyLine((Line)selectedShape, dialog.getLine());
					command.exec();
					return command;
				}

			} else if (selectedShape instanceof Rectangle) {

				Rectangle rect = (Rectangle) selectedShape;
				DlgRectangle dialog = new DlgRectangle();

				dialog.getTxtX().setText("" + Integer.toString(rect.getUpperLeftPoint().getX()));
				dialog.getTxtY().setText("" + Integer.toString(rect.getUpperLeftPoint().getY()));
				dialog.getTxtHeight().setText("" + Integer.toString(rect.getHeight()));
				dialog.getTxtWidth().setText("" + Integer.toString(rect.getWidth()));
				dialog.getBtnInnerColor().setBackground(rect.getInnerColor());
				dialog.getBtnOutlineColor().setBackground(rect.getColor());
				dialog.setModal(true);
				dialog.setVisible(true);

				if (dialog.isOk()) {
					CmdModifyRectangle command = new CmdModifyRectangle((Rectangle)selectedShape, dialog.getRectangle());
					command.exec();
					return command;
				}
			} else if (selectedShape instanceof HexagonAdapter) {
			    HexagonAdapter hexagon = (HexagonAdapter) selectedShape;
			    DlgHexagon dialog = new DlgHexagon();

			    int centerX = hexagon.getHexagon().getX();
			    int centerY = hexagon.getHexagon().getY();

			    dialog.getTxtX().setText("" + Integer.toString(centerX));
			    dialog.getTxtX().setEditable(false);
			    dialog.getTxtY().setText("" + Integer.toString(centerY));
			    dialog.getTxtY().setEditable(false);
			    dialog.getTxtR().setText("" + Integer.toString(hexagon.getHexagon().getR()));
			    dialog.getBtnColor().setBackground(hexagon.getHexagon().getBorderColor());
			    dialog.getBtnInnerColor().setBackground(hexagon.getHexagon().getAreaColor());
			    dialog.setModal(true);
			    dialog.setVisible(true);

			    if (dialog.isOk()) {
			    	CmdModifyHexagon command = new CmdModifyHexagon((HexagonAdapter)selectedShape, dialog.getHexagon());
					command.exec();
					return command;
			    }
			}

		}		
		return null;
	}

}
