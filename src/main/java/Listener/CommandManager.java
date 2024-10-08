package Listener;

import java.util.concurrent.ConcurrentHashMap;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import commands.NewtonCommand;
import commands.ClearCommand;
import commands.ServerCommand;



public class CommandManager {
	
	public ConcurrentHashMap<String, ServerCommand> commands; 
	
	public CommandManager() {
		this.commands = new ConcurrentHashMap<>(); 
		
		this.commands.put("clear", new ClearCommand());
		this.commands.put("newton", new NewtonCommand()); 
	}
	public boolean perform(String command, Member m, TextChannel channel, Message message) {
		
		ServerCommand cmd; 
		if((cmd = this.commands.get(command.toLowerCase())) != null) {
			cmd.performCommand(m, channel, message);
		}
		
		return false; 
	}

}
