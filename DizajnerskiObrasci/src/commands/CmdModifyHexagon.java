package commands;

import geometry.Donut;
import geometry.HexagonAdapter;

public class CmdModifyHexagon implements Command {
	private HexagonAdapter oldValue;
	private HexagonAdapter newValue;
	private HexagonAdapter originalValue = new HexagonAdapter();
	
	public CmdModifyHexagon(HexagonAdapter oldValue, HexagonAdapter newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		
	}
	@Override
	public void exec() {
		originalValue=(HexagonAdapter) oldValue.clone();
		oldValue.getHexagon().setX(newValue.getHexagon().getX());
		oldValue.getHexagon().setY(newValue.getHexagon().getY());	
		oldValue.getHexagon().setR(newValue.getHexagon().getR());
		
		oldValue.getHexagon().setBorderColor(newValue.getHexagon().getBorderColor());
		oldValue.getHexagon().setAreaColor(newValue.getHexagon().getAreaColor());
		
	}

	@Override
	public void unexec() {
		oldValue.getHexagon().setX(originalValue.getHexagon().getX());
		oldValue.getHexagon().setY(originalValue.getHexagon().getY());	
		oldValue.getHexagon().setR(originalValue.getHexagon().getR());
		
		oldValue.getHexagon().setBorderColor(originalValue.getHexagon().getBorderColor());
		oldValue.getHexagon().setAreaColor(originalValue.getHexagon().getAreaColor());
		
	}
	public HexagonAdapter getOriginal() {
		return originalValue;
	}
	public HexagonAdapter getNew() {
		return newValue;
	}

}
