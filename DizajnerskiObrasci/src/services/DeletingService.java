package services;

import commands.CmdDelete;
import mvc.DrawingModel;

public class DeletingService {

	public CmdDelete delete(DrawingModel model) {
		CmdDelete cmd = new CmdDelete();
		cmd.setModel(model);
		cmd.exec();
		return cmd;
	}

}
