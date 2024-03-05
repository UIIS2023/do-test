package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;

public class View extends JPanel {

	private DrawingModel model = new DrawingModel();
	
	public View() {
		setBackground(Color.WHITE);
	}
	
	public View(DrawingModel model) {
		super();
		this.model = model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getList().iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
		System.out.println(model.getList().size());
	}
}
