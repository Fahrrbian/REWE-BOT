package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResultManager {

	private DatabaseManager dbManager; 
	
		public ResultManager(DatabaseManager dbManager) {
			this.dbManager = dbManager; 
			}
		
		public void autoSave(Double result) {
			String sql = "INSERT INTO result_table (result) VALUES (?)";
			Connection conn = dbManager.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setDouble(1, result);
				pstmt.executeUpdate();
		        conn.commit();
		        System.out.println("Ergebnis gespeichert: " + result);
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		    }
		}
}
