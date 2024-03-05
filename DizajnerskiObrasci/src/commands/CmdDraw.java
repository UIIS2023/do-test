package commands;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdDraw implements Command {

	private DrawingModel model;
	private Shape s;
	public CmdDraw(Shape shape, DrawingModel model) {
		this.s = shape;
		this.model=model;
	}
	
	@Override
	public void exec() {
		model.getList().add(s);
		
	}

	@Override
	public void unexec() {
		model.getList().remove(s);
		
	}
	
	public Shape getShape() {
		return s;
	}

}
