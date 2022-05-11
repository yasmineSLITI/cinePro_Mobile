/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CineProMobile.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import edu.CineProMobile.services.ServiceSalle;

/**
 *
 * @author user
 */
public class ListSalleForm extends Form {
    Form current;
    public ListSalleForm(Form previous) {
        current=this;
        setTitle("Liste des salles");

        SpanLabel sp = new SpanLabel();
//        System.out.println("Size == "+ServiceSalle.getInstance().getAllSalles().size());
        sp.setText(ServiceSalle.getInstance().getAllSalles().toString());
        add(sp);
        
        
        getToolbar().addCommandToSideMenu("Dashbord",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new edu.CineProMabile.gui.DashboardAdmin().show(); 
            }
        });
        getToolbar().addCommandToSideMenu("Salles",null, e-> current.showBack());
        getToolbar().addCommandToSideMenu("Evenements",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new EvenetListForm(current).show(); 
            }
        });
        getToolbar().addCommandToSideMenu("Demande de sponsoring",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new ListSalleForm(current).show(); 
            }
        });
        getToolbar().addCommandToSideMenu("Evenement en attente",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new EvenementEnAtteneListForm(current).show(); 
            }
        });
        getToolbar().addCommandToSideMenu("Stats",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new SatsSalleForm().createPieChartForm(current).show(); 
            }
        });
       
        getToolbar().addCommandToRightBar("+",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new AjouterSalleForm(current).show(); 
            }
        });
        
    }
    
}
