/*
 * Michael Neas
 * KMeans Implementation
 * Assignment 3
 * April 3, 2016
 */

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.ui.ApplicationFrame; 

public class Scatterplot extends ApplicationFrame { 

	/**
	 * Put in as reference id
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Scatterplot will take data and a title and use Application Frame to print to screen
	 * Each scatterplot has a bank of data and various data series based on how many clusters are formed
	 * JFreeChart was an excellent plugin 
	 * http://www.jfree.org/jfreechart/
	 * Which also requires: http://www.jfree.org/jcommon/
	 */
	public Scatterplot(String title, Kmeans kmeansdata) { 

		super(title); 

		XYSeriesCollection dataset = new XYSeriesCollection(); 

		XYSeries randSeries = new XYSeries("Random Selections"); 
		for(int i = 0; i<kmeansdata.getCentroids().size(); i++){
			randSeries.add(kmeansdata.getCentroids().get(i).getX(), kmeansdata.getCentroids().get(i).getY()); 
		}
		dataset.addSeries(randSeries); 

		for(int n = 0; n < kmeansdata.getCentroids().size(); n++){
			XYSeries rawData = new XYSeries("The Data Section " + n);
			for(int i = 0; i<kmeansdata.getPoints().size(); i++){
				if(kmeansdata.getPoints().get(i).getId() == n){
					rawData.add(kmeansdata.getPoints().get(i).getX(), kmeansdata.getPoints().get(i).getY());
				}
			}
			dataset.addSeries(rawData);
		}

		/*
		 * Constructor given in documentation.
		 */
		JFreeChart chart = ChartFactory.createScatterPlot( 
				title, // title 
				"X1", "X2", // axis labels 
				dataset, // dataset 
				PlotOrientation.VERTICAL, 
				true, // legend? yes 
				true, // tooltips? yes 
				false // URLs? no 
				); 
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 400)); 
		setContentPane(chartPanel); 

	}
}