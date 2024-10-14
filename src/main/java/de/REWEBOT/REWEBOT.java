package de.REWEBOT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.login.LoginException;

import Database.DatabaseManager;
import Listener.CommandListener;
import Listener.CommandManager;
import commands.ClearCommand; 
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class REWEBOT {
	
	public static REWEBOT INSTANCE; 
	
	private static DatabaseManager dbManager; 
	public ShardManager shardManager;
	private CommandManager cmdMan; 
	private Thread loop;
	
	public static void main(String[] args)  {
		try {
			new REWEBOT(); 
		} catch (LoginException | IllegalArgumentException e) {
		
			e.printStackTrace(); 
		}
	}
	
	public REWEBOT() throws LoginException, IllegalArgumentException {
		INSTANCE = this; 
		
		DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(""); 
		
		builder.enableIntents(GatewayIntent.MESSAGE_CONTENT); 
        builder.setActivity(Activity.playing("Bereit zum Rechnen."));
        builder.setStatus(OnlineStatus.ONLINE); 
        
        this.cmdMan = new CommandManager(); 
        
        builder.addEventListeners(new CommandListener()); 
        System.out.println("Event Listener hinzugefügt.");
        // Baue den ShardManager
        shardManager= builder.build();
        System.out.println("Bot online");
        REWEBOT.connectToDatabase(); 
        
        shutdown(); 
        //runLoop();

	}
	 public void shutdown() {
	    	new Thread(() -> {
	    		String line = ""; 
	    		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
	    		try {
	    			while((line = reader.readLine()) != null) {
	    				if(line.equalsIgnoreCase("exit")) {
	    					if(shardManager != null) {
	    						shardManager.setStatus(OnlineStatus.OFFLINE); 
	    						shardManager.shutdown();
	    						System.out.println("Bot offline.");
	     					}
	    					if(loop != null)  {
	    						loop.interrupt(); 
	     					}
	    					
	    					reader.close(); 
	    				}
	    				else {
	    					System.out.println("Use 'exit' to shutdown");
	    				}
	    			}
	    		} catch(IOException e) {
	    			e.printStackTrace(); 
	    		}
	    	}).start();  
	    }
	   public CommandManager getCmdMan() {
	    	return cmdMan; 
	    }
	   private static void connectToDatabase() {
		   try {
			dbManager = new DatabaseManager();
			dbManager.connect(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   }
}
