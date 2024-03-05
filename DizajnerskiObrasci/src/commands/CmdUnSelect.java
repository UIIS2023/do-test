package commands;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdUnSelect implements Command {

	private ArrayList<Shape> selectedShapes;
	private DrawingModel model;
	
	@Override
	public void exec() {
		selectedShapes = new ArrayList<Shape>();
		for (Shape shape : model.getList()) {
			if (shape.isSelected()) {
				selectedShapes.add(shape);
				shape.setSelected(false);
			}
		}
		
	}

	@Override
	public void unexec() {
		// TODO Auto-generated method stub
		for (Shape shape : selectedShapes) {
			shape.setSelected(true);
		}
	}
	
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public ArrayList<Shape> getShapes() {
		return selectedShapes;
	}

}
