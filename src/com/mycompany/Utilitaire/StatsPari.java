/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.Entite.FichePari;
import com.mycompany.Service.ServicePari;


/**
 *
 * @author skanderbejaoui
 */
public class StatsPari {
    
         /**
     * Creates a renderer for the specified colors.
     */
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(10);
        renderer.setLegendTextSize(10);
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
            if(k==0){
            series.add("Jetons misés" , (values[0]));
            }
             if(k==1){
            series.add("Jetons gagnés", values[1]);
            }
              if(k==2){
            series.add("Jetons perdus", values[2]);
            }
              k++;
        }

        return series;
    }
        public Form createPieChartForm(Form previous) {
            if(ServicePari.getListHistoriquePari(Session.getUser().getId()).isEmpty()){
                Container container_vide = new Container();
               Label vide= new Label("Vous n'avez aucun pari !");
               container_vide.add(vide);
                Form f = new Form("Mes stats");
             f.setLayout(new BorderLayout());
            f.addComponent(BorderLayout.CENTER, container_vide);
            Components.showBack(f, previous);
        return f;
            }
            else{
            double jeton_gagne = 0;
            double jeton_perdu =0;
            double jeton_mise = 0;
            
        // Generate the values
            for (FichePari fp : ServicePari.getListHistoriquePari(Session.getUser().getId())) {
                jeton_mise += fp.getMisetotal();
                if(fp.getEtat().equals(FichePari.EtatFiche.Gagne)){
                    jeton_gagne+=fp.getGain();
                }
                else if(fp.getEtat().equals(FichePari.EtatFiche.Perdu)){
                    jeton_perdu+=fp.getMisetotal();
                }
            }
            
        double[] values = new double[]{jeton_mise,jeton_gagne,jeton_perdu};
        
        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.YELLOW, ColorUtil.MAGENTA};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(10);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Mes stats", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form("Mes stats");
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, c);
        Components.showBack(f, previous);
        return f;
            }
    }
}
