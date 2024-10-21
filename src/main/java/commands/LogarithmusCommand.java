package commands;

import Database.ResultManager;
import interfaces.ServerCommand;
import master.BaseCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Mentions;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;



public class LogarithmusCommand extends BaseCommand implements ServerCommand {
 
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
		Mentions mentions = message.getMentions(); 
		
		String [] args = message.getContentDisplay().split(" "); 
		
		double x = Double.parseDouble(args[1]); 
		double Basis = Double.parseDouble(args[2]); 
		
		try {
		double result = Math.log(x) / Math.log(Basis); 
		this.resultManager.autoSave(result); 
		channel.sendMessage("Ergebnis des Logarithmus für " + result  + " mit Basis " + Basis).queue();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
}
