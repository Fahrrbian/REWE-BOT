package commands;

import java.sql.SQLException;

import Database.DatabaseManager;
import Database.FunctionManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class SaveFunctionCommand implements ServerCommand{

	private FunctionManager functionManager; 
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
		  if (functionManager == null) {
	            DatabaseManager dbManager = new DatabaseManager();
	            functionManager = new FunctionManager(dbManager);
	        }
		
		String[] parts = message.getContentDisplay().split(" ", 3);
		
		
    	if(parts.length >= 3) {    		
    		String functionName = parts[1]; 
    		String expression = parts[2];    		    		 
    		try {
    		    functionManager.addFunction(functionName, expression);
    		    channel.sendMessage("Funktion hinzugefügt " + functionName).queue();
     		} catch (Exception e) {
    		    channel.sendMessage("Allgemeiner Fehler: " + e.getMessage()).queue();
    		    e.printStackTrace();
    		}			
    	}
	}
}