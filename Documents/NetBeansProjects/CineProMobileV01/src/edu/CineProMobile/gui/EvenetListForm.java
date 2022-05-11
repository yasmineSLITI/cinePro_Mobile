/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CineProMobile.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import edu.CineProMobile.services.ServiceEvenement;

/**
 *
 * @author user
 */
public class EvenetListForm   extends Form {
Form current;
    public EvenetListForm(Form previous) {
        setTitle("List tasks");
        
        SpanLabel sp = new SpanLabel();
//        int i=0;
//        while(i <= ServiceEvenement.getInstance().getAllEvenements().size()){Button cb=new Button();
//            cb.setText(ServiceEvenement.getInstance().getAllEvenements().toString());
//            i++;
//            add(cb);
//        }
        sp.setText(ServiceEvenement.getInstance().getAllEvenements().toString());
        add(sp);
        
        getToolbar().addCommandToSideMenu("Dashbord",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new edu.CineProMabile.gui.DashboardAdmin().show(); 
            }
        });
        getToolbar().addCommandToSideMenu("Salles",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new ListSalleForm(current).show(); 
            }
        });
        getToolbar().addCommandToSideMenu("Evenments en attentes",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new EvenementEnAtteneListForm(current).show(); 
            }
        });
        getToolbar().addCommandToSideMenu("Demande de sponsoring",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new ListSalleForm(current).show(); 
            }
        });
       
        getToolbar().addCommandToRightBar("+",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new AjouterEventForm(current).show(); 
            }
        });
        getToolbar().addCommandToSideMenu("Stats",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new StatsEventForm().createPieChartForm(current).show(); 
            }
        });
    }

}


