package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectManager {
	private DatabaseManager dbManager;
	 
	
	public SelectManager(DatabaseManager dbManager) {
		this.dbManager = dbManager; 
	}
	public static Map<String, String> selectLast10(DatabaseManager dbManager) {
		Map<String, String> functionNames = new HashMap<>(); 
		Statement stmt = null; 
		ResultSet rs= null;
		String sql = "SELECT * from functions ORDER BY id DESC LIMIT 10;"; 
		Connection conn = dbManager.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  
			  while (rs.next()) {
			        // Beispiel: Ausgabe der Funktion (je nach Struktur deiner Tabelle)
			        String functionName = rs.getString("name");
			        String function = rs.getString("expression"); 
			        functionNames.put(functionName, function); 
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
	public static Map<Integer, String> selectLast10Results(DatabaseManager dbManager) {
		Map<Integer, String> ids = new HashMap<>(); 
		Statement stmt = null; 
		ResultSet rs= null;
		String sql = "SELECT * from result_table ORDER BY id DESC LIMIT 10;"; 
		Connection conn = dbManager.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  
			  while (rs.next()) {
			        // Beispiel: Ausgabe der Funktion (je nach Struktur deiner Tabelle)
			        int id = rs.getInt("id");
			        String result = rs.getString("result");
			        ids.put(id, result); 
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
		return ids;
	}
}
