package master;

import java.util.function.Function;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class FunctionManagement {
	
	// Für allgemeine Funktionen, um sie darzustellen...
	public static Function<Double, Double> createFunction(String expression) {
		//ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript"); 
		return(x) -> {
			Expression expr = new ExpressionBuilder(expression)
					.variable("x")
					.build()
					.setVariable("x", x); 
			return expr.evaluate(); 
		}; 
	}
	
	//Für den Graphen der Funktion
	 public static double[] evaluateFunction(String formula, double[] xData) {
	        double[] yData = new double[xData.length];

	        // Erstelle einen Ausdruck basierend auf der Formel
	        Expression expression = new ExpressionBuilder(formula)
	                .variable("x")  // Wir setzen "x" als Variable für die Funktion
	                .build();

	        // Werte die Funktion für jedes x aus
	        for (int i = 0; i < xData.length; i++) {
	            expression.setVariable("x", xData[i]);
	            yData[i] = expression.evaluate();
	        }

	        return yData;
	    }
}
