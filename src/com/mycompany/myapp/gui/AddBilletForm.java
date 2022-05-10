/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.entities.Film;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceBillet;
import com.mycompany.myapp.services.ServiceProducts;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author Asus
 */
public class AddBilletForm extends Form {

    private Film film;

    public AddBilletForm(Film film) {
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                null
        );
        this.getToolbar().setTitle("Réserver " + film.getNomF());
        setUIID("Activate");
        this.setLayout(new FlowLayout(CENTER, CENTER));
        Container ctnCategorie = new Container(new FlowLayout(CENTER, CENTER));
        Container ctnNbPlace = new Container(new FlowLayout(CENTER, CENTER));

        this.film = film;
        Label LCaltegorie = new Label();
        LCaltegorie.setText("Veuillez choisir la catégorie de votre billet:");
        LCaltegorie.setFocusable(false);
        LCaltegorie.setUIID("Label");
        LCaltegorie.getAllStyles().setAlignment(Label.CENTER);

        ComboBox CategorieCB = new ComboBox();
        CategorieCB.addItem("First Class");
        CategorieCB.addItem("Second Class");
        CategorieCB.addItem("Third Class");
        ctnCategorie.addAll(LCaltegorie, CategorieCB);

        Label LNbPlace = new Label();
        LNbPlace.setText("Veuillez choisir le nombre de place:");
        LNbPlace.setFocusable(false);
        LNbPlace.setUIID("Label");
        LNbPlace.getAllStyles().setAlignment(Label.CENTER);
        TextField tfNbPlace = new TextField("", "Nombre de place");
        ctnNbPlace.addAll(LNbPlace, tfNbPlace);

        CategorieCB.setFocusable(false);
        CategorieCB.setUIID("ComboBox");
        CategorieCB.getAllStyles().setAlignment(ComboBox.CENTER);

        Button btnSubmit = new Button("Reserver");

        btnSubmit.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNbPlace.getText().length() == 0) {
                    Dialog.show("Alert", "Veuillez remplir le champ de Nombre de place", new Command("ok"));
                } else {

                    Billet c = new Billet();
                    String cat = CategorieCB.getSelectedItem().toString();
                    StringTokenizer st = new StringTokenizer(cat, ",");

                    c.setCategorieBillet(st.nextToken());
                    c.setNb_place(Integer.parseInt(tfNbPlace.getText()));
                    c.setIdReservation(film.getIdF());
                    c.setIdClient(1);

                    if (ServiceBillet.getInstance().addBillet(c)) {
                         new validerBilletForm(c).show();
                    } else {
                        Dialog.show("erreur", "connection Failed", new Command("ok"));

                    }

                }
            }

        }
        );
        this.addAll(ctnCategorie, ctnNbPlace, btnSubmit);

    }
}
