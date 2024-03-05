package strategy;

import java.io.IOException;

public class SaveManager implements Strategy {

	private Strategy strategy;
	
	@Override
	public void save(String filePath) throws IOException {
		strategy.save(filePath);
		
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	

}
