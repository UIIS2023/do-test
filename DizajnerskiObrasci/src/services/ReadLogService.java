package services;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import commands.CmdDelete;
import commands.CmdDraw;
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

public class ReadLogService {

	public List<String> readLogFile(String filePath) throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(filePath));
		List<String> logs = new ArrayList<String>();
		
		String line;
		while((line = buffer.readLine()) != null) {
			logs.add(line);
		}
		
		buffer.close();
		
		return logs;
	}

	public Command getCommand(String log, DrawingModel model) {
		Command c = null;
		
		if (log.startsWith("Add Point")) {
			String[] logParts = log.split(";");
			int x = Integer.parseInt( logParts[1].split("=")[1] );
			int y = Integer.parseInt( logParts[2].split("=")[1] );
			Color color = new Color(Integer.parseInt( logParts[3].split("=")[1] ));
			Point p = new Point();
			p.setX(x);
			p.setY(y);
			p.setColor(color);
			c = new CmdDraw(p, model);
		} else if (log.startsWith("Add Line")) {
			String[] logParts = log.split(";");
			int xStart = Integer.parseInt( logParts[1].split("=")[1] );
			int yStart = Integer.parseInt( logParts[2].split("=")[1] );
			int xEnd = Integer.parseInt( logParts[3].split("=")[1] );
			int yEnd = Integer.parseInt( logParts[4].split("=")[1] );
			Color color = new Color(Integer.parseInt( logParts[5].split("=")[1] ));
			Line line = new Line();
			line.setStartPoint(new Point(xStart, yStart));
			line.setEndPoint(new Point(xEnd, yEnd));
			line.setColor(color);
			c = new CmdDraw(line, model);
		} else if (log.startsWith("Add Circle")) {
			String[] logParts = log.split(";");
			int x = Integer.parseInt( logParts[1].split("=")[1] );
			int y = Integer.parseInt( logParts[2].split("=")[1] );
			int radius = Integer.parseInt( logParts[3].split("=")[1] );
			Color outColor = new Color(Integer.parseInt( logParts[4].split("=")[1] ));
			Color inColor = new Color(Integer.parseInt( logParts[5].split("=")[1] ));
			Circle cir = new Circle();
			cir.setCenter(new Point(x,y));
			try {
				cir.setRadius(radius);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cir.setColor(outColor);
			cir.setInnerColor(inColor);
			c = new CmdDraw(cir, model);
		} else if (log.startsWith("Add Donut")) {
			String[] logParts = log.split(";");
			int x = Integer.parseInt( logParts[1].split("=")[1] );
			int y = Integer.parseInt( logParts[2].split("=")[1] );
			int radius = Integer.parseInt( logParts[3].split("=")[1] );
			int innerRadius = Integer.parseInt( logParts[4].split("=")[1] );
			Color outColor = new Color(Integer.parseInt( logParts[5].split("=")[1] ));
			Color inColor = new Color(Integer.parseInt( logParts[6].split("=")[1] ));
			Donut don = new Donut();
			don.setCenter(new Point(x,y));
			try {
				don.setRadius(radius);
			} catch (Exception e) {
				e.printStackTrace();
			}
			don.setInnerRadius(innerRadius);
			don.setColor(outColor);
			don.setInnerColor(inColor);
			c = new CmdDraw(don, model);
		} else if (log.startsWith("Add Rectangle")) {
			String[] logParts = log.split(";");
			int x = Integer.parseInt( logParts[1].split("=")[1] );
			int y = Integer.parseInt( logParts[2].split("=")[1] );
			int width = Integer.parseInt( logParts[3].split("=")[1] );
			int height = Integer.parseInt( logParts[4].split("=")[1] );
			Color outColor = new Color(Integer.parseInt( logParts[5].split("=")[1] ));
			Color inColor = new Color(Integer.parseInt( logParts[6].split("=")[1] ));
			Rectangle rec = new Rectangle();
			rec.setUpperLeftPoint(new Point(x,y));
			rec.setWidth(width);
			rec.setHeight(height);
			rec.setColor(outColor);
			rec.setInnerColor(inColor);
			c = new CmdDraw(rec, model);
		} else if (log.startsWith("Add Hexagon")) {
			String[] logParts = log.split(";");
			int x = Integer.parseInt( logParts[1].split("=")[1] );
			int y = Integer.parseInt( logParts[2].split("=")[1] );
			int radius = Integer.parseInt( logParts[3].split("=")[1] );
			Color outColor = new Color(Integer.parseInt( logParts[4].split("=")[1] ));
			Color inColor = new Color(Integer.parseInt( logParts[5].split("=")[1] ));
			HexagonAdapter hex = new HexagonAdapter(new Point(x,y), radius, false, outColor, inColor);
			c = new CmdDraw(hex, model);
		} else if (log.startsWith("Modify Point")) {
			String[] logParts = log.split(" to ");
			String[] newPointParts = logParts[1].split(";");
			Shape selectedShape = model.getListSelected().get(0);
			int x = Integer.parseInt( newPointParts[1].split("=")[1] );
			int y = Integer.parseInt( newPointParts[2].split("=")[1] );
			Color color = new Color(Integer.parseInt( newPointParts[3].split("=")[1] ));
			Point p = new Point();
			p.setX(x);
			p.setY(y);
			p.setColor(color);
			c = new CmdModifyPoint((Point)selectedShape, p);
		} else if (log.startsWith("Modify Line")) {
			String[] logParts = log.split(" to ");
			String[] newLineParts = logParts[1].split(";");
			Shape selectedShape = model.getListSelected().get(0);
			int xStart = Integer.parseInt( newLineParts[1].split("=")[1] );
			int yStart = Integer.parseInt( newLineParts[2].split("=")[1] );
			int xEnd = Integer.parseInt( newLineParts[3].split("=")[1] );
			int yEnd = Integer.parseInt( newLineParts[4].split("=")[1] );
			Color color = new Color(Integer.parseInt( newLineParts[5].split("=")[1] ));
			Line line = new Line();
			line.setStartPoint(new Point(xStart, yStart));
			line.setEndPoint(new Point(xEnd, yEnd));
			line.setColor(color);
			c = new CmdModifyLine((Line)selectedShape, line);
		} else if (log.startsWith("Modify Circle")) {
			String[] logParts = log.split(" to ");
			String[] newCircleParts = logParts[1].split(";");
			Shape selectedShape = model.getListSelected().get(0);
			int x = Integer.parseInt( newCircleParts[1].split("=")[1] );
			int y = Integer.parseInt( newCircleParts[2].split("=")[1] );
			int radius = Integer.parseInt( newCircleParts[3].split("=")[1] );
			Color outColor = new Color(Integer.parseInt( newCircleParts[4].split("=")[1] ));
			Color inColor = new Color(Integer.parseInt( newCircleParts[5].split("=")[1] ));
			Circle cir = new Circle();
			cir.setCenter(new Point(x,y));
			try {
				cir.setRadius(radius);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cir.setColor(outColor);
			cir.setInnerColor(inColor);
			c = new CmdModifyCircle((Circle)selectedShape, cir);
		} else if (log.startsWith("Modify Donut")) {
			String[] logParts = log.split(" to ");
			String[] newDonutParts = logParts[1].split(";");
			Shape selectedShape = model.getListSelected().get(0);
			int x = Integer.parseInt( newDonutParts[1].split("=")[1] );
			int y = Integer.parseInt( newDonutParts[2].split("=")[1] );
			int radius = Integer.parseInt( newDonutParts[3].split("=")[1] );
			int innerRadius = Integer.parseInt( newDonutParts[4].split("=")[1] );
			Color outColor = new Color(Integer.parseInt( newDonutParts[5].split("=")[1] ));
			Color inColor = new Color(Integer.parseInt( newDonutParts[6].split("=")[1] ));
			Donut don = new Donut();
			don.setCenter(new Point(x,y));
			try {
				don.setRadius(radius);
			} catch (Exception e) {
				e.printStackTrace();
			}
			don.setInnerRadius(innerRadius);
			don.setColor(outColor);
			don.setInnerColor(inColor);
			c = new CmdModifyDonut((Donut)selectedShape, don);
		} else if (log.startsWith("Modify Rectangle")) {
			String[] logParts = log.split(" to ");
			String[] newRectangleParts = logParts[1].split(";");
			Shape selectedShape = model.getListSelected().get(0);
			int x = Integer.parseInt( newRectangleParts[1].split("=")[1] );
			int y = Integer.parseInt( newRectangleParts[2].split("=")[1] );
			int width = Integer.parseInt( newRectangleParts[3].split("=")[1] );
			int height = Integer.parseInt( newRectangleParts[4].split("=")[1] );
			Color outColor = new Color(Integer.parseInt( newRectangleParts[5].split("=")[1] ));
			Color inColor = new Color(Integer.parseInt( newRectangleParts[6].split("=")[1] ));
			Rectangle rec = new Rectangle();
			rec.setUpperLeftPoint(new Point(x,y));
			rec.setWidth(width);
			rec.setHeight(height);
			rec.setColor(outColor);
			rec.setInnerColor(inColor);
			c = new CmdModifyRectangle((Rectangle)selectedShape, rec);
		} else if (log.startsWith("Modify Hexagon")) {
			String[] logParts = log.split(" to ");
			String[] newHexagonParts = logParts[1].split(";");
			Shape selectedShape = model.getListSelected().get(0);
			int x = Integer.parseInt( newHexagonParts[1].split("=")[1] );
			int y = Integer.parseInt( newHexagonParts[2].split("=")[1] );
			int radius = Integer.parseInt( newHexagonParts[3].split("=")[1] );
			Color outColor = new Color(Integer.parseInt( newHexagonParts[4].split("=")[1] ));
			Color inColor = new Color(Integer.parseInt( newHexagonParts[5].split("=")[1] ));
			HexagonAdapter hex = new HexagonAdapter(new Point(x,y), radius, false, outColor, inColor);
			c = new CmdModifyHexagon((HexagonAdapter)selectedShape, hex);
		} else if (log.startsWith("Select")) {
			String[] logParts = log.split(":");
			String indexString = logParts[1];
			int index = Integer.parseInt(indexString);
			Shape s =model.getList().get(index);
			CmdSelect cmd = new CmdSelect();
			cmd.setSelectedShape(s);
			c = cmd;
		} else if (log.startsWith("Unselect")) {
			CmdUnSelect cmd = new CmdUnSelect();
			cmd.setModel(model);
			c = cmd;
		} else if (log.startsWith("Move up max") ) {
			CmdMoveUpMax cmd = new CmdMoveUpMax(model);
			c = cmd;
		} else if (log.startsWith("Move one up") ) {
			CmdMoveOneUp cmd = new CmdMoveOneUp(model);
			c = cmd;
		} else if (log.startsWith("Move one down") ) {
			CmdMoveOneDown cmd = new CmdMoveOneDown(model);
			c = cmd;
		} else if (log.startsWith("Move down max") ) {
			CmdMoveDownMax cmd = new CmdMoveDownMax(model);
			c = cmd;
		} else if (log.startsWith("Delete") ) {
			CmdDelete cmd = new CmdDelete();
			cmd.setModel(model);
			c = cmd;
		}
		
		 
		return c;
		
	}
}
