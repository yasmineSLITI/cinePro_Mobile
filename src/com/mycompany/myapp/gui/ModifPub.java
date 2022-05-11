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
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.services.PubService;

/**
 *
 * @author sarra
 */
public class ModifPub extends Form {

    

    Resources theme;

//    public ModifPub(Form current, Publication p) {
//            theme = UIManager.initFirstTheme("/theme");
//        setTitle("modifier une publication");
//        Label titre1 = new Label("titre");
//        TextField titre = new TextField(p.getTitre());
//        Label tfpre1 = new Label("imgpub");
//        TextField tfpre = new TextField(p.getImgPub());
//        Label tfadr1 = new Label("txtPub");
//        TextField tfadr = new TextField(p.getTxtPub());
//        Button btnValider = new Button("modifier pub");
//        btnValider.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                if ((titre.getText().length() == 0) || (tfpre.getText().length() == 0) || (tfadr.getText().length() == 0)) {
//                    Dialog.show("Alert", "Les champs sont vides , Veuillez les remplir", new Command("OK"));
//                } else {
//                    try {
//                        p.setIdPub(p.getIdPub());
//                        p.setTitre(titre.getText());
//                        p.setImgPub(tfpre.getText());
//                        p.setTxtPub(tfadr.getText());
//                        
//                        PubService.getInstance().UpdatePub(p);
//
//                        Dialog.show("", "La commande a été modifié avec succès", new Command("OK"));
//
//                    } catch (NumberFormatException e) {
//                        Dialog.show("ERROR", "Telephone must be a number", new Command("OK"));
//                    }
//
//                }
//
//            }
//        });
//        
//        addAll(titre,tfpre,tfadr,btnValider);
//        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
//    }

    ModifPub(Form current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ModifPub(Publication c, Form previous) {
        
         theme = UIManager.initFirstTheme("/theme");
        setTitle("modifier une publication");
        Label titre1 = new Label("titre");
        TextField titre = new TextField(c.getTitre());
        Label tfpre1 = new Label("imgpub");
        TextField tfpre = new TextField(c.getImgPub());
        Label tfadr1 = new Label("txtpub");
        TextField tfadr = new TextField(c.getTxtPub());
        Button btnValider = new Button("modifier pub");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((titre.getText().length() == 0) || (tfpre.getText().length() == 0) || (tfadr.getText().length() == 0)) {
                    Dialog.show("Alert", "Les champs sont vides , Veuillez les remplir", new Command("OK"));
                } else {
                    try {
                        c.setIdPub(c.getIdPub());
                        c.setTitre(titre.getText());
                        c.setImgPub(tfpre.getText());
                        c.setTxtPub(tfadr.getText());
                        
                        PubService.getInstance().UpdatePub(c);

                        Dialog.show("", "La publication a été modifié avec succès", new Command("OK"));

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "", new Command("OK"));
                    }

                }

            }
        });
        
        addAll(titre,tfpre,tfadr,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}
