package commands;

import geometry.Circle;
import geometry.Rectangle;

public class CmdModifyCircle implements Command{

	private Circle oldValue;
	private Circle newValue;
	private Circle originalValue = new Circle();
	
	
	public CmdModifyCircle(Circle oldValue, Circle newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		
	}

	@Override
	public void exec() { 
		originalValue=(Circle) oldValue.clone();
		System.out.println("OriginalValue je" + originalValue.toString() );
		oldValue.getCenter().setX(newValue.getCenter().getX());
		oldValue.getCenter().setY(newValue.getCenter().getY());
		try {
			oldValue.setRadius(newValue.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldValue.setColor(newValue.getColor());
		oldValue.setInnerColor(newValue.getInnerColor());

		
	}

	@Override
	public void unexec() {
		oldValue.getCenter().setX(originalValue.getCenter().getX());
		oldValue.getCenter().setY(originalValue.getCenter().getY());
		try {
			oldValue.setRadius(originalValue.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oldValue.setColor(originalValue.getColor());
		oldValue.setInnerColor(originalValue.getInnerColor());

	}
	public Circle getOriginal() {
		return originalValue;
	}
	public Circle getNew() {
		return newValue;
	}

}
