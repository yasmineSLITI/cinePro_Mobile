/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.services.ServiceBillet;
import com.mycompany.myapp.services.ServiceProducts;
import java.io.IOException;

/**
 *
 * @author Asus
 */
public class validerBilletForm extends BaseFormClient {

    public Billet billet;

    public validerBilletForm(Billet billet) {

        this.getToolbar().setTitle("Votre Billet");
        setUIID("Activate");
        super.addSideMenu();
        this.setLayout(new FlowLayout(CENTER,CENTER));

        this.billet = billet;
        //System.out.println(product);

        Container descriptionContainer = new Container(BoxLayout.xCenter());
        
        TextArea prixInitial = new TextArea("Prix Initial Avant Remise : "+ServiceBillet.getInstance().prixInitialBillet(billet.getNb_place(),billet.getCategorieBillet())+" DT");
        TextArea remise = new TextArea("Remise : "+ServiceBillet.getInstance().remise()+" %");
        int prixFinalBTVA=Integer.parseInt(ServiceBillet.getInstance().prixInitialBillet(billet.getNb_place(),billet.getCategorieBillet()))-Integer.parseInt(ServiceBillet.getInstance().remise());
        TextArea prixFinalAvantTVA = new TextArea("Prix Final Avant TVA : "+prixFinalBTVA+" DT");
        TextArea prixFinalApresTVA = new TextArea("Prix Final Aprés TVA : "+prixFinalBTVA*1.3+" DT");

        prixInitial.setEditable(false);
        prixInitial.setFocusable(false);
        prixInitial.setUIID("Label");
        prixInitial.getAllStyles().setAlignment(TextArea.CENTER);

        remise.setEditable(false);
        remise.setFocusable(false);
        remise.setUIID("Label");
        remise.getAllStyles().setAlignment(TextArea.CENTER);

        prixFinalAvantTVA.setEditable(false);
        prixFinalAvantTVA.setFocusable(false);
        prixFinalAvantTVA.setUIID("Label");
        prixFinalAvantTVA.getAllStyles().setAlignment(TextArea.CENTER);

        prixFinalApresTVA.setEditable(false);
        prixFinalApresTVA.setFocusable(false);
        prixFinalApresTVA.setUIID("Label");
        prixFinalApresTVA.getAllStyles().setAlignment(TextArea.CENTER);


        descriptionContainer.add(prixInitial);

        Button btnPayement = new Button("Payer");
        Button btnAnnulement = new Button("Annuler");
        btnPayement.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                    Cart cart = new Cart();
//                    cart.setProduct(product);
//                    if (ServiceCart.getInstance().addCart(cart)) {
//
//                    } else {
//                        Dialog.show("erreur", "connection Failed", new Command("ok"));
//
//                    }

            }
        }
        );
        this.addAll(descriptionContainer, remise, prixFinalAvantTVA, prixFinalApresTVA, btnPayement,btnAnnulement);
    }

}
