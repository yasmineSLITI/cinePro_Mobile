/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CineProMobile.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.GenericSpinner;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import edu.CineProMobile.entites.Salle;
import edu.CineProMobile.services.ServiceSalle;
import javafx.scene.control.DatePicker;

/**
 *
 * @author user
 */
public class AjouterSalleForm extends Form {

    Form current;

    public AjouterSalleForm(Form previous) {
        setTitle("Ajouter Salle");
        setLayout(BoxLayout.y());

        TextField tfName = new TextField("", "Nom de la salle");
        //TextField tfStatus= new TextField("", "Status: 0 - 1");
        Button btnValider = new Button("Ajouter salle");
        Picker datePicker = new Picker();
        Label lb = new Label("PS : Using our mobile app only allows you to create salle withe a capacité of 500 places ONLY ! If this is not enaugh please connect to either our descktop application or our web site <3 !");
        NumericSpinner sp = new NumericSpinner();
        sp.setMin(50);
        sp.setMax(1550);
        sp.setStep(50);
        datePicker.setType(Display.PICKER_TYPE_DATE);

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0)) {
                    Dialog.show("Alerte", "Veuillez remplir tous les champs !", new Command("OK"));
                } else {
                    try {
                        Salle t = new Salle();
                        t.setNomS(tfName.getText());
                        t.setDateDerniereMaintenance(datePicker.getValue().toString());
                        t.setCapacité(Float.parseFloat(Double.toString(sp.getValue())));
                        if (ServiceSalle.getInstance().addSalle(t)) {
                            Dialog.show("Salle ajoutée ! ", "", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Erreur serveur !", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }

        });
        getToolbar().addCommandToSideMenu("Dashbord", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new edu.CineProMabile.gui.DashboardAdmin().show();
            }
        });
        getToolbar().addCommandToSideMenu(
                getToolbar().addCommandToSideMenu("Salles", null, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new ListSalleForm(current).show(); }
                    }

                    ));
                    getToolbar() 
                        .addCommandToSideMenu("Evenements",null,new ActionListener() {
            @Override
                        public void actionPerformed
                        (ActionEvent evt
                        
                            ) {
               new EvenetListForm(current).show();
                        }
                    }

                    );
                    getToolbar() 
                        .addCommandToSideMenu("Demande de sponsoring",null,new ActionListener() {
            @Override
                        public void actionPerformed
                        (ActionEvent evt
                        
                            ) {
               new ListSalleForm(current).show();
                        }
                    }

                    );
       
                    
                    addAll(tfName, datePicker, sp, btnValider);
                    //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

                }
    }
