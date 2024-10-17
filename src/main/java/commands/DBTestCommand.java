package commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import Database.DatabaseManager;
import interfaces.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class DBTestCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
		
		 String insertSQL = "INSERT INTO test_table (name) VALUES(?)";
	     Connection conn = DatabaseManager.getInstance().getConnection();
		
          try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
              pstmt.setString(1, "Testname");
              pstmt.executeUpdate();
              System.out.println("Daten erfolgreich eingefügt.");
          }   catch (SQLException e) {
      System.out.println(e.getMessage());
  }
	}

}
