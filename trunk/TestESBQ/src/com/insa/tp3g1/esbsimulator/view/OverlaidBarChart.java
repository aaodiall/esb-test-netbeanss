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

import com.insa.tp3g1.esbsimulator.model.result.LinkConsumerProvider;
import com.insa.tp3g1.esbsimulator.model.result.ResponseTime;
import com.insa.tp3g1.esbsimulator.model.result.Result;
import com.insa.tp3g1.esbsimulator.model.result.TotalResult;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart overlaid
 * with a line chart.
 */
public class OverlaidBarChart extends ApplicationFrame {
    private final String min= "minResponseTime";
     private final String max= "maxResponseTime";
      private final String average= "averageResponseTime";
      private final String link= "Cons/Prov_N°";
    /**
     * Default constructor.
     *
     * @param title  the frame title.
     * @param result
     */
    public OverlaidBarChart(final String title, Result result) {

        super(title);

        
        // create the first dataset...
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        LinkConsumerProvider[] linkConsProv=result.getLinksConsumerProvider();
        for (LinkConsumerProvider linkConsProv1 : linkConsProv) {
            dataset1.addValue(Integer.parseInt(linkConsProv1.getAverageResponseTime()), average, link + linkConsProv1.getConsumerId());
        }
        
        
        

        // create the first renderer...
  //      final CategoryLabelGenerator generator = new StandardCategoryLabelGenerator();
        final CategoryItemRenderer renderer = new BarRenderer();
    //    renderer.setLabelGenerator(generator);
        renderer.setItemLabelsVisible(true);
        
        final CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);
        
        plot.setDomainAxis(new CategoryAxis("Category"));
        plot.setRangeAxis(new NumberAxis("Value (ms)"));

        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);

        // now create the second dataset and renderer...
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        
        for (LinkConsumerProvider linkConsProv1 : linkConsProv) {
            dataset2.addValue(Integer.parseInt(result.getTotalResult().getResponseTime().getMinResponseTime()), min, link + linkConsProv1.getConsumerId());
        }
        
        
    

        final CategoryItemRenderer renderer2 = new LineAndShapeRenderer();
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);
        
        

        // create the third dataset and renderer... 
       final ValueAxis rangeAxis2 = new NumberAxis("value (ms)");
        plot.setRangeAxis(1, rangeAxis2);

        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
        
        for (LinkConsumerProvider linkConsProv1 : linkConsProv) {
            dataset3.addValue(Integer.parseInt(result.getTotalResult().getResponseTime().getMaxResponseTime()), max, link + linkConsProv1.getConsumerId());
        }
      
        plot.setDataset(2, dataset3);
        final CategoryItemRenderer renderer3 = new LineAndShapeRenderer();
        plot.setRenderer(2, renderer3);
        plot.mapDatasetToRangeAxis(2, 1);
        // change the rendering order so the primary dataset appears "behind" the 
        // other datasets...
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("Response Time Chart");
      //  chart.setLegend(new StandardLegend());

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {
        ResponseTime responseTime = new ResponseTime("sec", "10", "6");
            TotalResult totalResult = new TotalResult("8", "10", responseTime);
            
            LinkConsumerProvider lcp1 = new LinkConsumerProvider("10", "1", "1");
            LinkConsumerProvider lcp2 = new LinkConsumerProvider("30", "2", "2");
             LinkConsumerProvider lcp3 = new LinkConsumerProvider("45", "3", "3");
               LinkConsumerProvider lcp4 = new LinkConsumerProvider("25", "4", "4");
                 LinkConsumerProvider lcp5 = new LinkConsumerProvider("20", "5", "5");
            LinkConsumerProvider linkConsProv[] = {lcp1, lcp2,lcp3,lcp4,lcp5};
            
            Result result = new Result(totalResult, linkConsProv);
        final OverlaidBarChart demo = new OverlaidBarChart("Response Time Chart",result);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
