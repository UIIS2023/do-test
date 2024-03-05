package mvc;

public class DrawingApp {

	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame(model);
		DrawingController ctrl = new DrawingController(model);
		ctrl.setFrame(frame);
		frame.setCtrl(ctrl);
		ctrl.addObserver(frame);
		frame.setVisible(true);
		ctrl.notifyObservers();
	}

}
