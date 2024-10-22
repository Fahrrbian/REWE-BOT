package Chart;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import Database.DatabaseManager;
import Database.SelectManager;
import master.BaseCommand;
import master.FunctionManagement;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ChartGenerator extends BaseCommand {
	  	
	
	    public File createChart(String functionName) throws SQLException, IOException {
	        // Abrufen der Funktion aus der Datenbank
	    		 
	    	String formula = SelectManager.selectFunction(dbManager, functionName); 
	    	
	        // Beispiel-Daten für x-Werte
	        double[] xData = new double[100];
	        for (int i = 0; i < 100; i++) {
	            xData[i] = i * 0.1; // x-Werte von 0 bis 10
	        }

	        // Berechnung der y-Werte basierend auf der Funktion
	        double[] yData = FunctionManagement.evaluateFunction(formula, xData);
	    	
	        // XY Chart erstellen
	        XYChart chart = new XYChartBuilder().width(600).height(400).title("Graph von " + functionName).xAxisTitle("X").yAxisTitle("f(X)").build();
	    	
	        // Chart-Design anpassen
	        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);

	        // Daten zur Serie hinzufügen
	        chart.addSeries(functionName, xData, yData);

	        // Grafik als PNG speichern
	        File chartFile = new File(functionName + "_chart.png");
	        BitmapEncoder.saveBitmap(chart, chartFile.getAbsolutePath(), BitmapEncoder.BitmapFormat.PNG);

	        return chartFile;	    		    			
	    }
}
