package commands;

import geometry.Line;
import geometry.Point;

public class CmdModifyLine  implements Command {

	private Line oldValue;
	private Line newValue;
	private Line originalValue = new Line();

	
	public CmdModifyLine(Line oldValue, Line newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		
	}

	@Override
	public void exec() {
		
		originalValue=(Line) oldValue.clone();
		
		oldValue.setStartPoint(newValue.getStartPoint());
		oldValue.setEndPoint(newValue.getEndPoint());
		oldValue.setColor(newValue.getColor());

		
	}

	@Override
	public void unexec() {
		oldValue.setStartPoint(originalValue.getStartPoint());
		oldValue.setEndPoint(originalValue.getEndPoint());
		oldValue.setColor(originalValue.getColor());

	}
	public Line getOriginal() {
		return originalValue;
	}
	public Line getNew() {
		return newValue;
	}

}
