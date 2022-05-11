/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
public class AjoutPanierForm extends Form{
    public  AjoutPanierForm(Form previous) {
        setTitle("Ajouter au panier");
        setLayout(BoxLayout.y());
        
        Button btnValider = new Button("Ajouter au panier");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                  
                        Panier p = new Panier("4");
                        if( ServicePanier.getInstance().addPanier(p))
                        {
                           Dialog.show("Success","Facture ajoutée",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                   
                    
                }
                
                
            
        });
        
        addAll(btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
