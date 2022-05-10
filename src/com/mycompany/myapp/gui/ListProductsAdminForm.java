/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProducts;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ListProductsAdminForm extends BaseFormAdmin {

    public ListProductsAdminForm() {

        super.addSideMenu();
      
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
            Form addForm = new AddProductForm(this);
            addForm.show();
        });

        setTitle("Liste des produits");
        setUIID("Activate");

        this.setScrollable(false);
        this.setLayout(new BorderLayout());
        TextField searchBar = new TextField("", "Search");
        Button searchBtn = new Button("", FontImage.MATERIAL_SEARCH, "Link");
        Container searchCtn = new Container(BoxLayout.x());
        searchCtn.addAll(searchBar, searchBtn);
        this.add(BorderLayout.NORTH, searchCtn);
        ArrayList<Produit> gamesList = ServiceProducts.getInstance().getAllProducts();
        Container gamesCtn = new Container();
        gamesCtn.setScrollableY(true);
        searchBar.addActionListener(e -> {
            gamesCtn.removeAll();
            for (Produit g : gamesList) {
                if (g.getDesignation().toLowerCase().contains(searchBar.getText().toLowerCase()) || searchBar.getText().equals("")) {
                    gamesCtn.add(this.addProductsHolder(g));
                    gamesCtn.add(createLineSeparator());
                }
            }
        });
        for (Produit g : gamesList) {
            gamesCtn.add(this.addProductsHolder(g));
            gamesCtn.add(createLineSeparator());
        }
        this.add(BorderLayout.CENTER, gamesCtn);

    }

    public Container addProductsHolder(Produit c) {
        Container ctn = new Container(BoxLayout.y());
        Container ctninfo = new Container(new FlowLayout(CENTER, CENTER));

        try {

            Label lbNameProduct = new Label();
            Label lbPrice = new Label();

            lbNameProduct.setText(c.getDesignation().toLowerCase());
            lbPrice.setText(c.getPrixVenteUnit() + "DT");

            ctninfo.addAll(lbNameProduct, lbPrice);
            //img loading
            Image img;
            ImageViewer imgv;
            EncodedImage enc;
            try {
                enc = EncodedImage.create("/spinner.png");

                String url = "http://localhost/cinePro/public/uploads/" + c.getImage();
               // String url = Statics​.BASE_URL + "/images/products/" + c.getImage();


                System.out.println(url);
                img = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);

                imgv = new ImageViewer(img);

                imgv.addPointerReleasedListener((evnt) -> {
                    Form details = new ProductAdminForm(c, this);
                    details.show();
                });

                ctn.addAll(imgv, ctninfo);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        return ctn;
    }

}
