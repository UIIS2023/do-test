package commands;


import geometry.Shape;
import mvc.DrawingModel;

public class CmdMoveUpMax implements Command {

	private DrawingModel model;
	private Shape shape;
	private int position;
	
	public CmdMoveUpMax(DrawingModel model) {
		
		this.model=model;
		this.shape=model.getListSelected().get(0);
		
	}
	
	@Override
	public void exec() {
		position=model.getList().indexOf(shape);
		   model.getList().remove(shape);
		   model.getList().add(shape);
	}

	@Override
	public void unexec() {
		model.getList().remove(shape);
		model.getList().add(position, shape);
	}
	public Shape getShape() {
		return shape;
	}

	
}
