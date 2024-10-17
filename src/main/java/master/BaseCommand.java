package master;

import Database.DatabaseManager;
import Database.FunctionManager;
import Database.ResultManager;

public abstract class BaseCommand {
	
	protected ResultManager resultManager; 
	protected FunctionManager functionManager; 
	
	public BaseCommand() {
		DatabaseManager dbManager = new DatabaseManager(); 
		this.functionManager = new FunctionManager(dbManager); 
		this.resultManager = new ResultManager(dbManager); 
		System.out.println("ResultManager initialized: " + (this.resultManager != null));
	}
}
