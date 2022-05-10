/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProducts;
import java.io.IOException;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author Asus
 */
public class ProductAdminForm extends Form {

    private Produit product;

    public ProductAdminForm(Produit product, Form previous) {
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        this.getToolbar().setTitle(product.getDesignation());
        setUIID("Activate");
        this.setLayout(BoxLayout.y());
   

        this.product = product;

        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_EDIT, e -> {
                Form editForm = new EditProductForm(this.product, this);
                editForm.show();
        });
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_DELETE, e -> {
                ServiceProducts.getInstance().deleteProduct(this.product.getIDProduit());
                new ListProductsAdminForm().show();
        });

        try {
            //System.out.println(product);
            EncodedImage spinner = EncodedImage.create("/spinner.png");
            Container imageContainer = new Container(BoxLayout.xCenter());
            Container descriptionContainer = new Container(BoxLayout.xCenter());
            String url = "http://localhost/cinePro/public/uploads/" + product.getImage();
            System.out.println(url);
            Image gameImage = URLImage.createToStorage(spinner, url, url, URLImage.RESIZE_SCALE);

            gameImage = gameImage.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
            ImageViewer image = new ImageViewer(gameImage);
            imageContainer.setHeight(Display.getInstance().getDisplayHeight() / 3);

            TextArea descriptionTA = new TextArea(product.getDescription());
            TextArea tfName = new TextArea(product.getDesignation());
            TextArea tfPrixAchatUnit = new TextArea("Prix Achat Unit : "+product.getPrixAchatUnit() + " DT");
            TextArea tfPrixVenteUnit = new TextArea("Prix Vente Unit : "+product.getPrixVenteUnit() + " DT");
            TextArea tfQuantityStocked = new TextArea("Quantité En Stock : "+product.getQuantiteEnStock() + " unités");

            descriptionTA.setEditable(false);
            descriptionTA.setFocusable(false);
            descriptionTA.setUIID("Label");
            descriptionTA.getAllStyles().setAlignment(TextArea.CENTER);

            tfName.setEditable(false);
            tfName.setFocusable(false);
            tfName.setUIID("Label");
            tfName.getAllStyles().setAlignment(TextArea.CENTER);

            tfPrixAchatUnit.setEditable(false);
            tfPrixAchatUnit.setFocusable(false);
            tfPrixAchatUnit.setUIID("Label");
            tfPrixAchatUnit.getAllStyles().setAlignment(TextArea.CENTER);

            tfPrixVenteUnit.setEditable(false);
            tfPrixVenteUnit.setFocusable(false);
            tfPrixVenteUnit.setUIID("Label");
            tfPrixVenteUnit.getAllStyles().setAlignment(TextArea.CENTER);

            tfQuantityStocked.setEditable(false);
            tfQuantityStocked.setFocusable(false);
            tfQuantityStocked.setUIID("Label");
            tfQuantityStocked.getAllStyles().setAlignment(TextArea.CENTER);

            descriptionContainer.add(descriptionTA);
            imageContainer.add(image);
            
       
            this.addAll(imageContainer, tfName ,descriptionContainer, tfPrixAchatUnit, tfPrixVenteUnit, tfQuantityStocked);
        } catch (IOException e) {
            System.err.println(e);
        }

    }

}
