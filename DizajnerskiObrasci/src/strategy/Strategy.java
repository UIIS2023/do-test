package strategy;

import java.io.IOException;

public interface Strategy {

	void save(String filePath) throws IOException;
	
}
