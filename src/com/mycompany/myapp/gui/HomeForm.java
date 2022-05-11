/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;;

/**
 *
 * @author hp
 */
public class HomeForm extends Form {
    Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        //Button btnAddTask = new Button("Ajouter Facture");
        Button btnListTasks = new Button("Liste Facture");
        Button btnaddpanier = new Button("Ajouter au panier");
         Button btnlistepanier = new Button("Consulter  panier");
        
        //btnAddTask.addActionListener(e-> new AjoutFactureForm(current).show());
        btnListTasks.addActionListener(e-> new ListFactureForm(current).show());
        btnaddpanier.addActionListener(e-> new AjoutPanierForm(current).show());
        btnlistepanier .addActionListener(e-> new ListePanierForm(current).show());

        addAll(btnListTasks,btnaddpanier,btnlistepanier );
        
        
    }
}
