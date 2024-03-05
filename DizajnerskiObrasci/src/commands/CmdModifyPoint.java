package commands;

import geometry.Point;

public class CmdModifyPoint implements Command {

	private Point oldValue;
	private Point newValue;
	private Point originalValue = new Point();


	
	public CmdModifyPoint(Point oldValue, Point newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
       
		
	}
	
	@Override
	public void exec() {
		
		originalValue=(Point) oldValue.clone();
		oldValue.setX(newValue.getX());
		oldValue.setY(newValue.getY());
		oldValue.setColor(newValue.getColor());
		

	}

	@Override
	public void unexec() {
		oldValue.setX(originalValue.getX());
		oldValue.setY(originalValue.getY());
		oldValue.setColor(originalValue.getColor());
		
	}
	public Point getOriginal() {
		return originalValue;
	}
	public Point getNew() {
		return newValue;
	}

}
