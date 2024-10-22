package commands;


import java.util.List;
import java.util.Map;

import Database.DatabaseManager;
import Database.SelectManager;
import interfaces.ServerCommand;
import master.BaseCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class ViewFunctionsCommand extends BaseCommand implements ServerCommand{ 
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
	
		try {
			Map<String, String> functionNames = SelectManager.selectLast10Functions(dbManager);
			
			for (Map.Entry<String, String> entry : functionNames.entrySet()) {
				channel.sendMessage("Function Name: " + entry.getKey() + ", Function: "  + entry.getValue()).queue();
			}
		} catch(Exception e) {
			channel.sendMessage("Fehler ").queue(); 
			e.printStackTrace();
		}
	}
}
