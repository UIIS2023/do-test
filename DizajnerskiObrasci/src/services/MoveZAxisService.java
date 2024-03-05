package services;

import commands.CmdMoveDownMax;
import commands.CmdMoveOneDown;
import commands.CmdMoveOneUp;
import commands.CmdMoveUpMax;
import commands.Command;
import mvc.DrawingModel;

public class MoveZAxisService {

	public Command moveOneUp(DrawingModel model) {
		CmdMoveOneUp command = new CmdMoveOneUp(model);
		command.exec();
		return command;
	}
	
	public Command moveUpMax(DrawingModel model) {
		CmdMoveUpMax command = new CmdMoveUpMax(model);
		command.exec();
		return command;
	}
	
	public Command moveOneDown(DrawingModel model) {
		CmdMoveOneDown command = new CmdMoveOneDown(model);
		command.exec();
		return command;
	}
	
	public Command moveDownMax(DrawingModel model) {
		CmdMoveDownMax command = new CmdMoveDownMax(model);
		command.exec();
		return command;
	}
}
