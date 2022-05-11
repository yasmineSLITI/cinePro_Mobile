
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CineProMobile.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import edu.CineProMobile.services.ServiceEvenement;
import edu.CineProMobile.services.ServiceSalle;

/**
 *
 * @author user
 */
public class StatsEventForm  extends Form {
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    for (double value : values) {
        if(k==0)
        series.add("Demande acceptée "  , value);
        if(k==1)
        series.add("Demande en attente ", value);
        k++;
    }

    return series;
}

public Form createPieChartForm(Form prev) {
    // Generate the values
    double[] values = new double[]{ServiceEvenement.getInstance().getAllEvenements().size(),ServiceEvenement.getInstance().getAllEvenementsEnAttente().size()};
    
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.rgb(140,208, 148),ColorUtil.rgb(255,127,80)};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
     r.setGradientStart(0, ColorUtil.rgb(140,208, 148));
    r.setGradientStop(0,ColorUtil.rgb(140,208, 148));
    r.setGradientEnabled(true);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Acceptation des demande d'evenement", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Acceptation des demande d'evenement", new BorderLayout());
    f.add(BorderLayout.CENTER, c);
    f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
    return f;

}
}   

