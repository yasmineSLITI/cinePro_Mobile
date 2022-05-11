/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.services.PubService;
import java.util.ArrayList;

/**
 *
 * @author sarra
 */
public class ListPub extends Form {

    Resources theme;
    String ss = "";
    Form current;
    ArrayList<Publication> c;

    public ListPub(Form previous) {
//        setTitle("liste des publications");
//        SpanLabel sp = new SpanLabel();
//        sp.setText(PubService.getInstance().getAll().toString());
//        add(sp);
 current = this;
        setLayout(BoxLayout.y());
// Container titleCmp = BoxLayout.encloseY(
//                        //FlowLayout.encloseIn(menuButton),
//                        BorderLayout.centerAbsolute(
//                                BoxLayout.encloseY(
//                                    new Label("Liste Des Commandes", "Title")
//                                )
//                            )
//                );
//        
        for (Publication c : PubService.getInstance().getAll()) {

            Label tfname = new Label();
            tfname.setText("Titre : " + c.getTitre());
            tfname.getAllStyles().setFgColor(0x149414);
            Label tfpre = new Label();
            tfpre.setText("Image : " + c.getImgPub());
            Label tfadr = new Label();
            tfadr.setText("Description : " + c.getTxtPub());

            Container cnt1 = new Container(BoxLayout.x());
            Container cnt2 = new Container(BoxLayout.y());
            Container cnt3 = new Container(BoxLayout.x());
            Button tfdelete = new Button(FontImage.MATERIAL_DELETE);
            Button tfModifier = new Button("Modifier");

            cnt2.addAll(tfname, tfpre, tfadr);
            cnt3.addAll(tfdelete, tfModifier);
//
            add( cnt2);
            add(cnt3);

  try {
            tfModifier.addActionListener((e1) -> {
              
               
                new ModifPub(c, current).show();
                   
               
            });                     
             } catch (Exception e) {System.out.println(e.getMessage());
                }

  

            tfdelete.addActionListener((e) -> {
                if (Dialog.show("Alert", "Voulez vous supprimer la publication  " + c.getIdPub() + " !!", "OK", "Cancel")) {
                    if (PubService.getInstance().supprimerPub(c.getIdPub())) {
                        ToastBar.showMessage("La publication a été supprimé", FontImage.MATERIAL_WARNING);
                        PubService.getInstance().getAll();
                        new ListPub(previous).show();
                    } else {
                        new ListPub(previous).show();
                    }
                }

            });
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        }

    }}
