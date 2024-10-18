package commands;


import java.util.List;

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
			List<String> funcitonNames = SelectManager.selectLast10(dbManager);
			
			for (String name : funcitonNames) {
				channel.sendMessage("Function Name: " + name).queue();
			}
		} catch(Exception e) {
			channel.sendMessage("Fehler ").queue(); 
			e.printStackTrace();
		}
	}
}
