package commands;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdMoveOneDown  implements Command {

	private DrawingModel model;
	private Shape shape;
	private int position;
	
	public CmdMoveOneDown(DrawingModel model) {
		
		this.model=model;
		this.shape=model.getListSelected().get(0);
		
	}
	
	@Override
	public void exec() {
		position = model.getList().indexOf(shape);
			Collections.swap(model.getList(), position-1, position);
		
		
	}

	@Override
	public void unexec() {
		Collections.swap(model.getList(), position, position-1);
	}
	public Shape getShape() {
		return shape;
	}
}
