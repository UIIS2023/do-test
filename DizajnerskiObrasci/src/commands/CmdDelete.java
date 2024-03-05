package commands;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdDelete implements Command {

	private ArrayList<Shape> selectedShapes;
	private ArrayList<Integer> selectedIndexes;
	private DrawingModel model;
	
	@Override
	public void exec() {
		selectedShapes = new ArrayList<Shape>();
		selectedIndexes = new ArrayList<Integer>();
		for (Shape shape : model.getList()) {
			if (shape.isSelected()) {
				selectedShapes.add(shape);
				selectedIndexes.add(model.getList().indexOf(shape));
			}
		}
		System.out.println(selectedIndexes.size());
		for (Shape s :  selectedShapes) {
			model.getList().remove(s);
		}
		
	}

	@Override
	public void unexec() {
		for (int i = 0; i < selectedShapes.size(); i++) {
			model.getList().add(selectedIndexes.get(i), selectedShapes.get(i));
		}
	}
	
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public ArrayList<Integer> getIndexes() {
		return selectedIndexes;
	}


}
