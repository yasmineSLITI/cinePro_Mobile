/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.services.ServiceFacture;
import com.mycompany.myapp.services.ServicePanier;

/**
 *
 * @author hp
 */
public class ListePanierForm extends Form{
    Form current;
     public  ListePanierForm(Form previous) {
        setTitle("Liste panier");
        setLayout(BoxLayout.y());
        

        SpanLabel sp = new SpanLabel();
        sp.setText(ServicePanier.getInstance().getAllPanier().toString());
        Button btnValider = new Button("Vider panier");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                  
                        Panier p = new Panier();
                        if( ServicePanier.getInstance().removePanier(p))
                        {
                           Dialog.show("Success","panier supprimée",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                   
                    
                }
                
                
            
        });
         Button btnValiderr = new Button("Ajouter Facture");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                  
                        Facture f = new Facture("1");
                        if( ServiceFacture.getInstance().addFacture(f))
                        {
                           Dialog.show("Success","Facture ajoutée",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                   
                    
                }
                
                
            
        });
        btnValiderr.addActionListener(e-> new AjoutFactureForm(current).show());
        
        addAll(btnValider,sp,btnValiderr);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
