package Listener;

import java.util.concurrent.ConcurrentHashMap;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import commands.NewtonCommand;
import commands.SaveFunctionCommand;
import interfaces.ServerCommand;
import commands.ClearCommand;
import commands.DBTestCommand;
import commands.IterationsCommand;
import commands.LogarithmusCommand;
import commands.ViewFunctionsCommand;
import commands.ViewResultsCommand;
import commands.NLogarithmusCommand;

public class CommandManager {
	
	public ConcurrentHashMap<String, ServerCommand> commands; 
	
	public CommandManager() {
		this.commands = new ConcurrentHashMap<>(); 
		
		this.commands.put("clear", new ClearCommand());
		this.commands.put("newton", new NewtonCommand());
		this.commands.put("iteration", new IterationsCommand()); 
		this.commands.put("save", new SaveFunctionCommand()); 
		this.commands.put("database", new DBTestCommand());
		this.commands.put("functions", new ViewFunctionsCommand());
		this.commands.put("log", new LogarithmusCommand()); 
		this.commands.put("ln", new NLogarithmusCommand());
		this.commands.put("ergebnisse", new ViewResultsCommand()); 
	}
	public boolean perform(String command, Member m, TextChannel channel, Message message) {
		
		ServerCommand cmd; 
		if((cmd = this.commands.get(command.toLowerCase())) != null) {
			cmd.runCommand(m, channel, message);
		}
		
		return false; 
	}

}
