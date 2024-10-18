package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	private static DatabaseManager instance;
	private Connection conn; 
	
	public static DatabaseManager getInstance() {
	        if (instance == null) {
	            instance = new DatabaseManager();
	        }
	        return instance;
	    }
	public DatabaseManager() {
		connect(); 
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
        try {
            if (conn == null || conn.isClosed()) {
                connect(); // Versuche, die Verbindung erneut herzustellen
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Überprüfen der Verbindung: " + e.getMessage());
        }
        return conn;
    }
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Verbindung zur SQLite-Datenbank geschlossen.");
            } catch (SQLException e) {
                System.out.println("Fehler beim Schließen der Verbindung: " + e.getMessage());
            }
        }
    }
}
