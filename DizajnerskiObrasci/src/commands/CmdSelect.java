package commands;

import geometry.Shape;

public class CmdSelect implements Command {

	private Shape shape;
	
	@Override
	public void exec() {
		shape.setSelected(true);
		
	}

	@Override
	public void unexec() {
		// TODO Auto-generated method stub
		shape.setSelected(false);
		
	}
	
	public void setSelectedShape(Shape shape) {
		this.shape = shape;
	}
	
	public Shape getShape() {
		return shape;
	}

}
