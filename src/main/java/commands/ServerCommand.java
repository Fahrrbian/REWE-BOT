package commands;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public interface ServerCommand {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	
		default void runCommand(Member m, TextChannel channel, Message message) {
			executor.submit(() -> performCommand(m, channel, message)); 
		}
		void performCommand(Member m, TextChannel channel, Message message); 

	
//	public void performCommand(Member m, TextChannel channel, Message message);
}