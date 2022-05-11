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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.spinner.TimeSpinner;
import edu.CineProMobile.entites.Evenement;
import edu.CineProMobile.entites.Salle;
import edu.CineProMobile.services.ServiceEvenement;
import edu.CineProMobile.services.ServiceSalle;

/**
 *
 * @author user
 */
public class AjouterEventForm extends Form {
 Form current;

    public AjouterEventForm(Form previous) {
        setTitle("Ajouter Evenement");
        setLayout(BoxLayout.y());

        TextField tfName = new TextField("", "Nom de l'evenement");
        
        TextField tfDes = new TextField("", "Description de l'evenement");
        
        //TextField tfStatus= new TextField("", "Status: 0 - 1");
        Button btnValider = new Button("Envoyer demande");
        //Picker datePicker = new Picker();
        Label lb = new Label("PS : Using our mobile app only allows you to create salle withe a capacité of 500 places ONLY ! If this is not enaugh please connect to either our descktop application or our web site <3 !");
        NumericSpinner spD = new NumericSpinner();
        spD.setMin(45);
        spD.setMax(180);
        spD.setStep(5);
        NumericSpinner spM = new NumericSpinner();
        spM.setMin(5000);
        spM.setMax(20000);
        spM.setStep(100);
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0)) {
                    Dialog.show("Alerte", "Veuillez remplir tous les champs !", new Command("OK"));
                } else {
                    try {
                        Evenement t = new Evenement();
                        t.setDescription(tfDes.getText());
                        t.setNomev(tfName.getText());
                        t.setDuree((int)spD.getValue());
                        t.setMontant((int)spM.getValue());
                        if (ServiceEvenement.getInstance().addEvenement(t,1)) {
                            Dialog.show("Demande envoyée ! ", "", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Erreur serveur !", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }

        });
       
       
                    
                    addAll(tfName, spM,spD,tfDes , btnValider);
                    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

                }   
}
