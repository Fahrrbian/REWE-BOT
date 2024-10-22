package commands;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import Chart.ChartGenerator;
import Database.SelectManager;
import interfaces.ServerCommand;
import master.BaseCommand;
import master.ChartMaster;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Mentions;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class PlotCommand extends ChartMaster implements ServerCommand {
 
	
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
		
		Mentions mentions = message.getMentions(); 
		
		String [] args = message.getContentDisplay().split(" "); 
		
		String functionName = args[1]; 
		 

		try {
			File chartFile = chartGenerator.createChart(functionName);
			channel.sendMessage("Diagramm: ").addFiles(net.dv8tion.jda.api.utils.FileUpload.fromData(chartFile)
			        ).queue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
