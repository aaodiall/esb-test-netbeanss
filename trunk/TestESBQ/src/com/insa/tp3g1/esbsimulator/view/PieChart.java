/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.view;

/**
 *
 * @author alpha
 */
import com.insa.tp3g1.esbsimulator.model.result.Result;
import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import java.util.HashMap;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;


public class PieChart extends JFrame {

  private static final long serialVersionUID = 1L;

  public PieChart( String chartTitle,int lost) {
      //  super(applicationTitle);
        // This will create the dataset 
        PieDataset dataset = createDataset(lost);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);

    }
    
    
/** * Creates a sample dataset */
//HashMap<String,Integer> values
    private  PieDataset createDataset(int lost) {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Lost="+lost+"%", 29);
        result.setValue("succecced="+(100-lost)+"%", 100-lost);
       
        return result;
        
    }
    
    
/** * Creates a chart */

    private JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart(title,          // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
        
    }
  
   public static void main(String[] args) {
          PieChart demo = new PieChart("Messages",10);
          demo.pack();
          demo.setVisible(true);
      }

} 