package strategy;

import java.io.FileWriter;
import java.io.IOException;

public class SaveLogStrategy implements Strategy {

	private String logs;
	
	public SaveLogStrategy(String text) {
		this.logs = text;
	}
	
	@Override
	public void save(String filePath) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		fw.write(logs);
		fw.close();
	}

}
