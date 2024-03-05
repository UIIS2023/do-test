package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import geometry.Shape;

public class FIleService {

	public List<Shape> readFile() {
		JFileChooser jFileChooser = new JFileChooser(new File("C:\\"));
		jFileChooser.setDialogTitle("Open file");
		
		List<Shape> list = new ArrayList<Shape>();
		
		int result = jFileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			String path = jFileChooser.getSelectedFile().getAbsolutePath();
			
			ObjectInputStream is;
			try {
				is = new ObjectInputStream(new FileInputStream(path));
				list = (List<Shape>)is.readObject();
				is.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"File not loaded.","Error!",JOptionPane.WARNING_MESSAGE);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null,"File not loaded.","Error!",JOptionPane.WARNING_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"File not loaded.","Error!",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		return list;
	}
}
