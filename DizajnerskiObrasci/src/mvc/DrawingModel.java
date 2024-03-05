package mvc;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;

public class DrawingModel {

	private List<Shape> db = new ArrayList<Shape>();
	
	public List<Shape> getList() {
		return db;
	}
	
	public List<Shape> getListSelected() {
		List<Shape> list = new ArrayList<Shape>();
		for (Shape s : db) {
			if (s.isSelected()) {
				list.add(s);
			}
		}
		
		return list;
	}
	
}
