package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectManager {
	private DatabaseManager dbManager;
	 
	
	public SelectManager(DatabaseManager dbManager) {
		this.dbManager = dbManager; 
	}
	public static List<String> selectLast10(DatabaseManager dbManager) {
		List<String> functionNames = new ArrayList<>(); 
		Statement stmt = null; 
		ResultSet rs= null;
		String sql = "SELECT * from functions;"; 
		Connection conn = dbManager.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  
			  while (rs.next()) {
			        // Beispiel: Ausgabe der Funktion (je nach Struktur deiner Tabelle)
			        String functionName = rs.getString("name");
			        functionNames.add(functionName); 
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    // Resourcen schließen
		    try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		    try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		    try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return functionNames;
	}
}
