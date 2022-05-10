/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
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

/**
 *
 * @author Asus
 */
public class EditProductForm extends Form {
    
    String ch;

    public EditProductForm(Produit product, Form previous) {
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        setTitle("Edit Product");
        setUIID("Activate");
        setLayout(BoxLayout.y());
        // this.setUIID("Activate");
        TextField tfName = new TextField("", "Designation Produit");
        TextField tfDes = new TextField("", "Description Produit");
        TextField tfPrixAchatUnit = new TextField("", "Prix Achat Unitaire");
        TextField tfPrixVenteUnit = new TextField("", "Prix Vente Unitaire");
        TextField tfQuantityStocked = new TextField("", "Quantité en stock");
        
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

        tfName.setText(product.getDesignation());
        tfPrixAchatUnit.setText(product.getPrixAchatUnit() + "");
        tfPrixVenteUnit.setText(product.getPrixVenteUnit() + "");
        tfQuantityStocked.setText(product.getQuantiteEnStock() + "");
        tfDes.setText(product.getDescription());
        
        Container photoContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));

        photoContainer.add(imageLabel);
        photoContainer.add(imageField);
        photoContainer.add(selectImage);

        Button btnSubmit = new Button("Edit Product");

        btnSubmit.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) || (tfDes.getText().length() == 0)
                        || (tfPrixAchatUnit.getText().length() == 0) || (tfPrixVenteUnit.getText().length() == 0) || (tfQuantityStocked.getText().length() == 0)) {
                    Dialog.show("Alert", "please fill all the Fields", new Command("ok"));
                } else {

                    product.setDescription(tfDes.getText());
                    product.setDesignation(tfName.getText());
                    product.setPrixAchatUnit(Double.parseDouble(tfPrixAchatUnit.getText()));
                    product.setPrixVenteUnit(Double.parseDouble(tfPrixVenteUnit.getText()));
                    product.setQuantiteEnStock(Integer.parseInt(tfQuantityStocked.getText()));

                    if (ServiceProducts.getInstance().EditProduct(product)) {
                        new ListProductsAdminForm().show();
                    } else {
                        Dialog.show("erreur", "connection Failed", new Command("ok"));

                    }

                }
            }

        }
        );
        addAll(tfName, tfDes,photoContainer, tfPrixAchatUnit,tfPrixVenteUnit, tfQuantityStocked, btnSubmit);

    }

}
