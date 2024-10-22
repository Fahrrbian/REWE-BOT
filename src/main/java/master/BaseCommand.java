package master;

import Database.DatabaseManager;
import Database.FunctionManager;
import Database.ResultManager;
import Database.SelectManager;

public abstract class BaseCommand {
	
	protected DatabaseManager dbManager; 
	protected ResultManager resultManager; 
	protected FunctionManager functionManager; 
	protected SelectManager selectManager;
	 
	public BaseCommand() {
		this.dbManager = ServiceLocator.getDatabaseManager(); 
		this.functionManager = new FunctionManager(dbManager); 
		this.resultManager = new ResultManager(dbManager);
		this.selectManager = new SelectManager(dbManager);  
	}
}
