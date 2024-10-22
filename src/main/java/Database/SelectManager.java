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
import java.util.function.Function;



public class SelectManager {
    private static DatabaseManager dbManager;

    public SelectManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }
    
    // Generische Methode, die eine SQL-Abfrage ausführt und eine Ergebnisverarbeitungsstrategie akzeptiert.
    public static <T> T executeQuery(String sql, Function<ResultSet, T> resultProcessor, Object... params) {
        T result = null;
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
              

        	   if (params != null && params.length > 0) {
                   for (int i = 0; i < params.length; i++) {
                       stmt.setObject(i + 1, params[i]); // setze Parameter dynamisch
                   }
               }
        	
            // Verarbeitung des ResultSets durch die übergebene Funktion
        	   try (ResultSet rs = stmt.executeQuery()) {                  
                   result = resultProcessor.apply(rs);
               }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Beispiel für eine konkrete Abfrage: Auswahl der letzten 10 Funktionen
    public static Map<String, String> selectLast10Functions(DatabaseManager dbManager) {
        String sql = "SELECT * from functions ORDER BY id DESC LIMIT 10;";
        return executeQuery(sql, rs -> {
            Map<String, String> functionNames = new HashMap<>();
            try {
                while (rs.next()) {
                    String functionName = rs.getString("name");
                    String function = rs.getString("expression");
                    functionNames.put(functionName, function);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return functionNames;
        });
    }

    // Beispiel für eine andere Abfrage: Auswahl der letzten 10 Ergebnisse
    public static Map<Integer, String> selectLast10Results(DatabaseManager dbManager) {
        String sql = "SELECT * from result_table ORDER BY id DESC LIMIT 10;";
        return executeQuery(sql, rs -> {
            Map<Integer, String> ids = new HashMap<>();
            try {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String result = rs.getString("result");
                    ids.put(id, result);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ids;
        });
    }
        public static String selectFunction(DatabaseManager dbManager, String functionName) {
            String sql = "SELECT * from functions WHERE name = ?;";
            return executeQuery(sql, rs -> {
                String result = null;
                try {
                    if (rs.next()) {
                        result = rs.getString("expression");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return result;
            }, functionName);
        }
}
