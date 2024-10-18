package master;

import Database.DatabaseManager;

public class ServiceLocator {
	private static DatabaseManager dbManager = new DatabaseManager(); 
	
	public static DatabaseManager getDatabaseManager() {
		return dbManager; 
	}
}
