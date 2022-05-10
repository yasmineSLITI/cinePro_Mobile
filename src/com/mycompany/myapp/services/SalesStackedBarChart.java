/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.BarChart;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class SalesStackedBarChart extends AbstractDemoChart {

    /**
     * Returns the chart name.
     *
     * @return the chart name
     */
    public String getName() {
        return "Vente de billets par mois";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    public String getDesc() {
        return "The monthly sales for the last 2 years (stacked bar chart)";
    }

    /**
     * Executes the chart demo.
     *
     * @param context the context
     * @return the built intent
     */
    public Form execute() {
       
        String[] titles = new String[]{"2022"};
        List<double[]> values = new ArrayList<double[]>();
        values.add(new double[]{Double.parseDouble(ServiceProducts.getInstance().JanuarySales()), Double.parseDouble(ServiceProducts.getInstance().FebuarySales()), Double.parseDouble(ServiceProducts.getInstance().MarchSales()), Double.parseDouble(ServiceProducts.getInstance().AprilSales()), Double.parseDouble(ServiceProducts.getInstance().MaySales()), Double.parseDouble(ServiceProducts.getInstance().JuneSales()), Double.parseDouble(ServiceProducts.getInstance().JulySales()), Double.parseDouble(ServiceProducts.getInstance().AugustSales()), Double.parseDouble(ServiceProducts.getInstance().septemberSales()), Double.parseDouble(ServiceProducts.getInstance().OctoberSales()), Double.parseDouble(ServiceProducts.getInstance().novemberSales()), Double.parseDouble(ServiceProducts.getInstance().decemberSales())});
           
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.CYAN};
        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
        setChartSettings(renderer, "", "Mois", "Unités Vendus", 0.5,
                12.5, 0, 15, ColorUtil.GRAY, ColorUtil.LTGRAY);

        ((XYSeriesRenderer) renderer.getSeriesRendererAt(0)).setDisplayChartValues(true);
        renderer.setXLabels(12);
        renderer.setYLabels(10);
        renderer.setXLabelsAlign(Component.LEFT);
        renderer.setYLabelsAlign(Component.LEFT);
        renderer.setPanEnabled(true, false);
        // renderer.setZoomEnabled(false);
        renderer.setZoomRate(1.1f);
        renderer.setBarSpacing(0.5f);

        BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
                BarChart.Type.STACKED);
        return wrap("", new ChartComponent(chart));

    }

}
