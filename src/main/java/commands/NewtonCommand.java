package commands;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.List;
import java.util.function.Function;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Mentions;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class NewtonCommand implements ServerCommand  {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
		
		Mentions mentions = message.getMentions(); 
		
		String [] args = message.getContentDisplay().split(" "); 
		
		if(args.length < 6) {			
			   channel.sendMessage("Bitte geben Sie 5 Argumente ein: <Startwert> <Toleranz> <MaxIter> <Funktion> <Ableitung>").queue();
	           return;
			}
			List<TextChannel> mentionedChannels = mentions.getChannels(TextChannel.class);  
			
		
			if(!mentionedChannels.isEmpty()) {
			    TextChannel tc = mentionedChannels.get(0);
			    //String[] args = message.getContentDisplay().split(" "); 
				}
					
			double x0 = Double.parseDouble(args[1]); 
			double tol = Double.parseDouble(args[2]); 
			int maxIter = Integer.parseInt(args[3]); 
			Function<Double, Double> f = createFunction(args[4]); 
			Function<Double, Double> df =  createFunction(args[5]);
			
			double xn = x0; 
			
	        for (int n = 0; n < maxIter; n++) {
	            double fxn = f.apply(xn);  // Funktionswert f(x) an der Stelle xn

	            // Abbruchbedingung: Wenn der Funktionswert nahe genug bei Null ist
	            if (Math.abs(fxn) < tol) {
	                channel.sendMessage("Die Nullstelle ist: " + String.format("%.4f", xn)).queue();
	                return;
	            }

	            double dfxn = df.apply(xn);  // Ableitung f'(x) an der Stelle xn

	            // Wenn die Ableitung null ist, kann das Verfahren nicht fortfahren
	            if (dfxn == 0.0) {
	            	channel.sendMessage("Ableitung ist Null. Keine Nullstelle gefunden.").queue();
	                return;  // Rückgabe von "NaN" (Not-a-Number) im Fehlerfall
	            }

	            // Aktualisierung des Näherungswertes xn
	            xn = xn - fxn / dfxn;
	        }

	        // Wenn die maximale Anzahl an Iterationen erreicht wird
	        System.out.println("Maximale Anzahl an Iterationen erreicht. Keine Nullstelle gefunden.");channel.sendMessage("Maximale Anzahl an Iterationen erreicht. Keine Nullstelle gefunden.").queue();	        
	    }
	
	private Function<Double, Double> createFunction(String expression) { 
		return(x) -> {
			Expression expr = new ExpressionBuilder(expression)
					.variable("x")
					.build()
					.setVariable("x", x); 
			return expr.evaluate(); 
		}; 
	}
			
		
}
