package services;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import commands.CmdSelect;
import commands.CmdUnSelect;
import commands.Command;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class SelectService {

	public Command handleSelection(MouseEvent e, DrawingModel model) {
		boolean notFoundShape=true;
		Point p=new Point(e.getX(),e.getY());
		List<Shape> selectedShapes = null ;
		for (int i = model.getList().size() - 1; i >= 0; i--) {
			Shape shape = model.getList().get(i);
			if(shape.contains(p.getX(), p.getY()))
			{
				notFoundShape=false;
				
				if(!shape.isSelected())
				{
					CmdSelect cmd = new CmdSelect();
					cmd.setSelectedShape(shape);
					return cmd;
				}
				else 
				{
					List<Shape> placeholder = new ArrayList<Shape>();
					placeholder.add(shape);
					CmdUnSelect cmdUnselect = new CmdUnSelect();
					cmdUnselect.setModel(model);
					return cmdUnselect;
				}
			}			
		}
		if(notFoundShape) {
			boolean hasSelectedShape = false;
			for (Shape s : model.getList()) {
				if (s.isSelected())
					hasSelectedShape = true;
			}
			
			if (hasSelectedShape) {
				CmdUnSelect cmdUnselect = new CmdUnSelect();
				cmdUnselect.setModel(model);
				return cmdUnselect;
			}
		}
		
		return null;
	}
}
