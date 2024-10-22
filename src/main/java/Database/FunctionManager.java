package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Function;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class FunctionManager {
	 private DatabaseManager dbManager;

	    public FunctionManager(DatabaseManager dbManager) {
	        this.dbManager = dbManager;
	    }

	    public void addFunction(String name, String expression) {
	        String sql = "INSERT INTO functions (name, expression) VALUES (?, ?)";
	        Connection conn = dbManager.getConnection();
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {	            	           
	        	pstmt.setString(1, name);
	            pstmt.setString(2, expression);
	            pstmt.executeUpdate();
	            conn.commit();
	            System.out.println("Funktion hinzugefügt: " + name);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	}