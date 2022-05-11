/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author sarra
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this;
        setTitle("home");
        setLayout(BoxLayout.y());
        
        add(new Label("choose an option"));
        Button btnAddPub = new Button("ajouter publication");
        Button btnListPub = new Button("afficher publication");
        Button btnModifPub = new Button("modifier publication");

       btnAddPub.addActionListener(e-> new AddPub(current).show() );
       btnListPub.addActionListener(e-> new ListPub(current).show());
       //btnModifPub .addActionListener(e-> new ModifPub(current).show());
       addAll(btnAddPub,btnListPub,btnModifPub);
       
    }
    
}
