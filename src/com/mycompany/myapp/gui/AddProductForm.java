/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProducts;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Asus
 */
public class AddProductForm extends Form {

    String ch;

    public AddProductForm(Form previous) {
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        setTitle("Add Product");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField("", "Designation Produit");
        TextField tfDes = new TextField("", "Description Produit");
        TextField tfPrixAchatUnit = new TextField("", "Prix Achat Unitaire");
        TextField tfPrixVenteUnit = new TextField("", "Prix Vente Unitaire");
        TextField tfQuantityStocked = new TextField("", "Quantity in stock");
//combo 

        //upload image
        Label imageLabel = new Label("Image");
        Button selectImage = new Button("Select");
        TextField imageField = new TextField("", "Select picture", 10, TextArea.ANY);
        imageField.setEditable(false);

        selectImage.addActionListener((evt) -> {
            Display.getInstance().openGallery((e) -> {
                if (e != null && e.getSource() != null) {
                    String filePath = (String) e.getSource();
                    imageField.setText(filePath.substring(filePath.lastIndexOf('/') + 1));
                    ch = filePath;
                }
            }, Display.GALLERY_IMAGE
            );

        }
        );

        Container photoContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));

        photoContainer.add(imageLabel);
        photoContainer.add(imageField);
        photoContainer.add(selectImage);

        Button btnSubmit = new Button("Submit");

        btnSubmit.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) || (tfDes.getText().length() == 0) || (imageField.getText().length() == 0)
                        || (tfPrixVenteUnit.getText().length() == 0) || (tfPrixAchatUnit.getText().length() == 0) || (tfQuantityStocked.getText().length() == 0)) {
                    Dialog.show("Alert", "merci de remplir tous les champs", new Command("ok"));
                } else {

                    Produit c = new Produit();

                    c.setDescription(tfDes.getText());
                    c.setImage(ch);
                    c.setDesignation(tfName.getText());
                    c.setPrixAchatUnit(Double.parseDouble(tfPrixAchatUnit.getText()));
                    c.setPrixVenteUnit(Double.parseDouble(tfPrixVenteUnit.getText()));

                    c.setQuantiteEnStock(Integer.parseInt(tfQuantityStocked.getText()));

                    if (ServiceProducts.getInstance().addProduct(c)) {
                        new ListProductsAdminForm().show();
                    } else {
                        Dialog.show("erreur", "connection Failed", new Command("ok"));

                    }

                }
            }

        }
        );
        addAll(tfName, tfDes, photoContainer, tfPrixAchatUnit, tfPrixVenteUnit, tfQuantityStocked, btnSubmit);

    }

}
