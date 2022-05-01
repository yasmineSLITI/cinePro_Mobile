/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.MyApplication;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseFormAdmin extends Form {

    public BaseFormAdmin() {
    }

    public BaseFormAdmin(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseFormAdmin(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu() {
        Toolbar tb = getToolbar();
        tb.setSafeArea(false);
        
        try {
            Image gameImage = Image.createImage("/profile.jpg");
            tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                    FlowLayout.encloseCenterBottom(
                            new Label(gameImage, "PictureWhiteBackgrond"))
            ));
        } catch (Exception e) {

        }

        tb.addMaterialCommandToSideMenu("Dashboard", FontImage.MATERIAL_DASHBOARD, null);
        tb.addMaterialCommandToSideMenu("Films", FontImage.MATERIAL_CAMERA, null);
        tb.addMaterialCommandToSideMenu("Compte", FontImage.MATERIAL_IMPORT_CONTACTS, null);
        tb.addMaterialCommandToSideMenu("Evénements", FontImage.MATERIAL_EVENT, null);
        tb.addMaterialCommandToSideMenu("Réservations", FontImage.MATERIAL_MEETING_ROOM, null);
        tb.addMaterialCommandToSideMenu("Salle", FontImage.MATERIAL_ROOM, null);
        tb.addMaterialCommandToSideMenu("Publications", FontImage.MATERIAL_PRINT, null);
        tb.addMaterialCommandToSideMenu("Produits", FontImage.MATERIAL_SHOPPING_BAG, null);
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, null);
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, null
        );
    }
}
