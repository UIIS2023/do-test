package strategy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class SaveFileStrategy implements Strategy {
	
	DrawingModel model;
	public SaveFileStrategy(DrawingModel model) {
	this.model=model;	
	}

	@Override
	public void save(String filePath) {
		
		List<Shape> list = new ArrayList<Shape>();
		for(Shape s : model.getList()) {
			list.add(s);
		}
		
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filePath));
			os.writeObject(list);
			os.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Not found", "Error",JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error", "Error",JOptionPane.WARNING_MESSAGE);
		}
		
	}

}
