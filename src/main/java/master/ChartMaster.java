package master;

import Chart.ChartGenerator;

public abstract class ChartMaster {
	
	protected ChartGenerator chartGenerator;
	
	public ChartMaster() {
		this.chartGenerator = new ChartGenerator(); 
	}
	
}
