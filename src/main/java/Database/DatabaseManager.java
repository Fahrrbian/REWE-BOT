package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	private static DatabaseManager instance;
	private Connection conn; 
	
	public DatabaseManager() {
		connect(); 
	}
	public static DatabaseManager getInstance() {
	        if (instance == null) {
	            instance = new DatabaseManager();
	        }
	        return instance;
	    }
	
    public void connect() {
        // SQLite connection string
        String url = "";
        
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Verbindung zur SQLite-Datenbank hergestellt.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Connection getConnection() {
       return conn;
    }

}
