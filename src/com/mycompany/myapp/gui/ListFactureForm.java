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
import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.services.ServiceFacture;
import com.mycompany.myapp.services.ServicePanier;

/**
 *
 * @author hp
 */
public class ListFactureForm extends Form{
    public ListFactureForm(Form previous) {
        setTitle("List Facture");

        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceFacture.getInstance().getAllFacture().toString());
         Button btnValider = new Button("Vider Facture");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                  
                        Facture f = new Facture();
                        if( ServiceFacture.getInstance().removeFacture(f))
                        {
                           Dialog.show("Success","Facture supprimée",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                   
                    
                }
                
                
            
        });
        add(sp);
        add(btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
