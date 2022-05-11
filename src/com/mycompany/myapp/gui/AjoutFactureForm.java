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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.services.ServiceFacture;

/**
 *
 * @author hp
 */
public class AjoutFactureForm extends Form {
    public AjoutFactureForm(Form previous) {
        setTitle("Ajouter Facture");
        setLayout(BoxLayout.y());
        
        Button btnValider = new Button("Ajouter Facture");
        
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
        
        addAll(btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
