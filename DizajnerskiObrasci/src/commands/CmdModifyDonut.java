package commands;

import geometry.Donut;
import geometry.Rectangle;

public class CmdModifyDonut implements Command{

	private Donut oldValue;
	private Donut newValue;
	private Donut originalValue = new Donut();
	
	
	public CmdModifyDonut(Donut oldValue, Donut newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		
	}

	@Override
	public void exec() { 
	
			originalValue=(Donut) oldValue.clone();

		
		oldValue.getCenter().setX(newValue.getCenter().getX());
		oldValue.getCenter().setY(newValue.getCenter().getY());
		try {
			oldValue.setRadius(newValue.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oldValue.setInnerRadius(newValue.getInnerRadius());
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
		
		oldValue.setInnerRadius(originalValue.getInnerRadius());
		
		
		oldValue.setColor(originalValue.getColor());
		oldValue.setInnerColor(originalValue.getInnerColor());

		
	}

	public Donut getOriginal() {
		return originalValue;
	}
	public Donut getNew() {
		return newValue;
	}
}
