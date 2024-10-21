package commands;

import java.util.Map;

import Database.SelectManager;
import interfaces.ServerCommand;
import master.BaseCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class ViewResultsCommand extends BaseCommand implements ServerCommand{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
		try {
			Map<Integer, String> results = SelectManager.selectLast10Results(dbManager);
			
			for(Map.Entry<Integer, String> entry : results.entrySet()) {
				channel.sendMessage("ID: " + entry.getKey() + ", Ergebnis: "  + entry.getValue()).queue(); 
				} 
			} catch (Exception e) {
				channel.sendMessage("Fehler"); 
				e.printStackTrace();
			}
		}
	}


