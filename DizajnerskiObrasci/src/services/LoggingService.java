package services;

import java.util.ArrayList;

import commands.CmdDelete;
import commands.CmdModifyCircle;
import commands.CmdModifyDonut;
import commands.CmdModifyHexagon;
import commands.CmdModifyLine;
import commands.CmdModifyPoint;
import commands.CmdModifyRectangle;
import commands.CmdMoveDownMax;
import commands.CmdMoveOneDown;
import commands.CmdMoveOneUp;
import commands.CmdMoveUpMax;
import commands.CmdSelect;
import commands.CmdUnSelect;
import commands.Command;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingModel;

public class LoggingService {

	public String logAddPoint(Point p) {
		String s = "Add Point;x="+p.getX()+";y=" + p.getY() + ";color=" +p.getColor().getRGB();
		return s;
	}
	
	public String logAddLine(Line l) {
		String s = "Add Line;xStart="+l.getStartPoint().getX()+";yStart=" + l.getStartPoint().getY() + ";xEnd="+l.getEndPoint().getX()+";yEnd=" + l.getEndPoint().getY() + ";color=" +l.getColor().getRGB();
		return s;
	}
	public String logAddRectangle(Rectangle r) {
		String s = "Add Rectangle;x="+r.getUpperLeftPoint().getX()+";y=" + r.getUpperLeftPoint().getY() + ";width="+r.getWidth()+";height=" + r.getHeight() + ";outColor=" +r.getColor().getRGB() + ";inColor=" +r.getInnerColor().getRGB();
		return s;
	}
	public String logAddCircle(Circle c) {
		String s = "Add Circle;x="+c.getCenter().getX()+";y=" + c.getCenter().getY() + ";radius="+c.getRadius()+ ";outColor=" +c.getColor().getRGB() + ";inColor=" +c.getInnerColor().getRGB();
		return s;
	}
	public String logAddDonut(Donut d) {
		String s = "Add Donut;x="+d.getCenter().getX()+";y=" + d.getCenter().getY() + ";radius="+d.getRadius()+ ";innerRadius="+d.getInnerRadius()+ ";outColor=" +d.getColor().getRGB() + ";inColor=" +d.getInnerColor().getRGB();
		return s;
	}
	public String logAddHexagon(HexagonAdapter h) {
		String s = "Add Hexagon;x="+h.getHexagon().getX()+";y=" + h.getHexagon().getY() + ";radius="+h.getHexagon().getR()+ ";outColor=" +h.getHexagon().getBorderColor().getRGB() + ";inColor=" +h.getHexagon().getAreaColor().getRGB();
		return s;
	}

	public String logAdd(Shape shape) {
		if (shape instanceof Point) {
			return logAddPoint((Point)shape);
		} else if (shape instanceof Line) {
			return logAddLine((Line)shape);
		} else if (shape instanceof Rectangle) {
			return logAddRectangle((Rectangle)shape);
		}else if (shape instanceof Donut) {
			return logAddDonut((Donut)shape);
		}else if (shape instanceof Circle) {
			return logAddCircle((Circle)shape);
		}else if (shape instanceof HexagonAdapter) {
			return logAddHexagon((HexagonAdapter)shape);
		}
		return "";
	}

	public String logSelectOrDeselect(DrawingModel model, Command cmd) {
		if (cmd instanceof CmdSelect) {
			CmdSelect cmdSel = (CmdSelect)cmd;
			return "Select on index:" + model.getList().indexOf(cmdSel.getShape());
		} else {
			CmdUnSelect cmdUnSel = (CmdUnSelect)cmd;
			ArrayList<Shape> shapes = cmdUnSel.getShapes();
			int lastIndex =shapes.size() - 1;
			String s = "Unselect on positions ";
			for (int i =0;i<=lastIndex;i++) {
				s += model.getList().indexOf(shapes.get(i));
				if (i!= lastIndex) {
					s+=";";
				}
			}
			return s;
		}
		
	}

	public String logMoveOneUp(DrawingModel model, CmdMoveOneUp command) {
		return "Move one up on index " + model.getList().indexOf(command.getShape());
		
	}

	public String logMoveUpMax(DrawingModel model, CmdMoveUpMax command) {
		return "Move up max on index " + model.getList().indexOf(command.getShape());
	}

	public String logMoveOneDown(DrawingModel model, CmdMoveOneDown cmd) {
		return "Move one down on index " + model.getList().indexOf(cmd.getShape());
	}
	public String logMoveDownMax(DrawingModel model, CmdMoveDownMax cmd) {
		return "Move down max on index " + model.getList().indexOf(cmd.getShape());
	}

	public String logModify(Command cmd) {
		if (cmd instanceof CmdModifyPoint) {
			CmdModifyPoint cmdModifyPoint=(CmdModifyPoint)cmd;
			String s = "Modify Point;x="+cmdModifyPoint.getOriginal().getX()+";y=" + cmdModifyPoint.getOriginal().getY() + ";color=" +cmdModifyPoint.getOriginal().getColor().getRGB()
					+ " to " + "Point;x="+cmdModifyPoint.getNew().getX()+";y=" + cmdModifyPoint.getNew().getY() + ";color=" +cmdModifyPoint.getNew().getColor().getRGB();
			return s;
		}else if (cmd instanceof CmdModifyLine) {
			CmdModifyLine cmdModifyLine=(CmdModifyLine)cmd;
			String s = "Modify Line;xStart="+cmdModifyLine.getOriginal().getStartPoint().getX()+";yStart=" + cmdModifyLine.getOriginal().getStartPoint().getY() + ";xEnd="+cmdModifyLine.getOriginal().getEndPoint().getX()+";yEnd=" + cmdModifyLine.getOriginal().getEndPoint().getY() + ";color=" +cmdModifyLine.getOriginal().getColor().getRGB()
					+ " to " + "Line;xStart="+cmdModifyLine.getNew().getStartPoint().getX()+";yStart=" + cmdModifyLine.getNew().getStartPoint().getY() + ";xEnd="+cmdModifyLine.getNew().getEndPoint().getX()+";yEnd=" + cmdModifyLine.getNew().getEndPoint().getY() + ";color=" +cmdModifyLine.getNew().getColor().getRGB();
			return s;
		}else if (cmd instanceof CmdModifyRectangle) {
			CmdModifyRectangle cmdModifyRectangle=(CmdModifyRectangle)cmd;
			String s = "Modify Rectangle;x="+cmdModifyRectangle.getOriginal().getUpperLeftPoint().getX()+";y=" + cmdModifyRectangle.getOriginal().getUpperLeftPoint().getY() + ";width="+cmdModifyRectangle.getOriginal().getWidth()+";height=" + cmdModifyRectangle.getOriginal().getHeight() + ";outColor=" +cmdModifyRectangle.getOriginal().getColor().getRGB() + ";inColor=" +cmdModifyRectangle.getOriginal().getInnerColor().getRGB()
					+ " to " + "Rectangle;x="+cmdModifyRectangle.getNew().getUpperLeftPoint().getX()+";y=" + cmdModifyRectangle.getNew().getUpperLeftPoint().getY() + ";width="+cmdModifyRectangle.getNew().getWidth()+";height=" + cmdModifyRectangle.getNew().getHeight() + ";outColor=" +cmdModifyRectangle.getNew().getColor().getRGB() + ";inColor=" +cmdModifyRectangle.getNew().getInnerColor().getRGB();
			return s;
		}else if (cmd instanceof CmdModifyCircle) {
			CmdModifyCircle cmdModifyCircle=(CmdModifyCircle)cmd;
			String s = "Modify Circle;x="+cmdModifyCircle.getOriginal().getCenter().getX()+";y=" + cmdModifyCircle.getOriginal().getCenter().getY() + ";radius="+cmdModifyCircle.getOriginal().getRadius()+ ";outColor=" +cmdModifyCircle.getOriginal().getColor().getRGB() + ";inColor=" +cmdModifyCircle.getOriginal().getInnerColor().getRGB()
					+ " to " + "Circle;x="+cmdModifyCircle.getNew().getCenter().getX()+";y=" + cmdModifyCircle.getNew().getCenter().getY() + ";radius="+cmdModifyCircle.getNew().getRadius()+ ";outColor=" +cmdModifyCircle.getNew().getColor().getRGB() + ";inColor=" +cmdModifyCircle.getNew().getInnerColor().getRGB();
			
			return s;
		}else if (cmd instanceof CmdModifyDonut) {
			CmdModifyDonut cmdModifyDonut=(CmdModifyDonut)cmd;
			String s = "Modify Donut;x="+cmdModifyDonut.getOriginal().getCenter().getX()+";y=" + cmdModifyDonut.getOriginal().getCenter().getY() + ";radius="+cmdModifyDonut.getOriginal().getRadius()+ ";innerRadius="+cmdModifyDonut.getOriginal().getInnerRadius()+ ";outColor=" +cmdModifyDonut.getOriginal().getColor().getRGB() + ";inColor=" +cmdModifyDonut.getOriginal().getInnerColor().getRGB()
					+ " to " + "Donut;x="+cmdModifyDonut.getNew().getCenter().getX()+";y=" + cmdModifyDonut.getNew().getCenter().getY() + ";radius="+cmdModifyDonut.getNew().getRadius()+ ";innerRadius="+cmdModifyDonut.getNew().getInnerRadius()+ ";outColor=" +cmdModifyDonut.getNew().getColor().getRGB() + ";inColor=" +cmdModifyDonut.getNew().getInnerColor().getRGB();
			return s;
		}else if (cmd instanceof CmdModifyHexagon) {
			CmdModifyHexagon cmdModifyHexagon=(CmdModifyHexagon)cmd;
			String s = "Modify Hexagon;x="+cmdModifyHexagon.getOriginal().getHexagon().getX()+";y=" + cmdModifyHexagon.getOriginal().getHexagon().getY() + ";radius="+cmdModifyHexagon.getOriginal().getHexagon().getR()+ ";outColor=" +cmdModifyHexagon.getOriginal().getHexagon().getBorderColor().getRGB() + ";inColor=" +cmdModifyHexagon.getOriginal().getHexagon().getAreaColor().getRGB()
					+ " to " + "Hexagon;x="+cmdModifyHexagon.getNew().getHexagon().getX()+";y=" + cmdModifyHexagon.getNew().getHexagon().getY() + ";radius="+cmdModifyHexagon.getNew().getHexagon().getR()+ ";outColor=" +cmdModifyHexagon.getNew().getHexagon().getBorderColor().getRGB() + ";inColor=" +cmdModifyHexagon.getNew().getHexagon().getAreaColor().getRGB();
			return s;
		}
		
		return null;
	}

	public String logDelete(CmdDelete cmd) {
		String s = "Delete ";
		for (Integer index:cmd.getIndexes()) {
			s += index + ",";
		}
		
		return s;
	}
}
 