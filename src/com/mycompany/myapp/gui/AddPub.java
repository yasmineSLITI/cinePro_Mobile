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
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.services.PubService;

/**
 *
 * @author sarra
 */
public class AddPub extends Form {

    public AddPub(Form previous) {
        setTitle("ajouter une nouvelle publication");
        TextField titre = new TextField("", "titre");
        TextField txtpub = new TextField("", "Description");
        TextField imgpub = new TextField("", "image");
        Button btnValider = new Button("ajouter pub");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((titre.getText().length()==0)||(txtpub.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Publication p = new Publication(titre.getText(), imgpub.getText(), txtpub.getText());
                        if( PubService.getInstance().addPub(p))
                        {
                           Dialog.show("Success","Publication ajoutée avec succée",new Command("OK"));
                        }else
                            Dialog.show("ERROR", " error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(titre,imgpub,txtpub,btnValider);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
