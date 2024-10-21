package commands;

import java.util.List;
import java.util.function.Function;

import Database.FunctionManager;
import interfaces.ServerCommand;
import master.BaseCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Mentions;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class IterationsCommand extends BaseCommand implements ServerCommand {

	 
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
		Mentions mentions = message.getMentions(); 
		
		String [] args = message.getContentDisplay().split(" "); 
		
		if(args.length < 5) {			
			   channel.sendMessage("Bitte geben Sie 4 Argumente ein: <a> <b> <Toleranz> <Funktion>").queue();
	           return;
			}
			List<TextChannel> mentionedChannels = mentions.getChannels(TextChannel.class);  
			
		
			if(!mentionedChannels.isEmpty()) {
			    TextChannel tc = mentionedChannels.get(0);
			    //String[] args = message.getContentDisplay().split(" "); 
				}
					
			double a = Double.parseDouble(args[1]); 
			double b = Double.parseDouble(args[2]); 
			double tol = Integer.parseInt(args[3]); 
			Function<Double, Double> f = FunctionManager.createFunction(args[4]); 
			
			if (f.apply(a) * f.apply(b) >= 0) {
		         channel.sendMessage("Keine Nullstelle gefunden, da f(a) und f(b) das gleiche Vorzeichen haben.").queue();
		         return;
		       }
			 								
			        double c = a;

			        while ((b - a) >= tol) {
			            c = (a + b) / 2;
			            if (f.apply(c) == 0.0) {
			                break; // Nullstelle gefunden
			            }
			            if (f.apply(c) * f.apply(a) <0) {
			                b = c; // Nullstelle im linken Intervall
			            } else {
			                a = c; // Nullstelle im rechten Intervall
			            }
			        }
			        this.resultManager.autoSave(c);
			        channel.sendMessage("Die Nullstelle ist: "+ String.format("%.4f%n", c)).queue();			        		   
	}
}

