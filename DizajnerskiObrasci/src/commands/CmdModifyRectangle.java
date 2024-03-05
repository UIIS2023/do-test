package commands;

import geometry.Line;
import geometry.Rectangle;

public class CmdModifyRectangle implements Command {
	private Rectangle oldValue;
	private Rectangle newValue;
	private Rectangle originalValue = new Rectangle();
	
	
	public CmdModifyRectangle(Rectangle oldValue, Rectangle newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		
	}

	@Override
	public void exec() { 
		
		originalValue=(Rectangle) oldValue.clone();
		
		
		oldValue.getUpperLeftPoint().setX(newValue.getUpperLeftPoint().getX());
		oldValue.getUpperLeftPoint().setY(newValue.getUpperLeftPoint().getY());
		
			oldValue.setHeight(newValue.getHeight());
		
			oldValue.setWidth(newValue.getWidth());
		
		oldValue.setColor(newValue.getColor());
		oldValue.setInnerColor(newValue.getInnerColor());


	}

	@Override
	public void unexec() {
		
		oldValue.getUpperLeftPoint().setX(originalValue.getUpperLeftPoint().getX());
		oldValue.getUpperLeftPoint().setY(originalValue.getUpperLeftPoint().getY());
		
			oldValue.setHeight(originalValue.getHeight());
		
			oldValue.setWidth(originalValue.getWidth());
		
		oldValue.setColor(originalValue.getColor());
		oldValue.setInnerColor(originalValue.getInnerColor());

	}
	public Rectangle getOriginal() {
		return originalValue;
	}
	public Rectangle getNew() {
		return newValue;
	}

}
