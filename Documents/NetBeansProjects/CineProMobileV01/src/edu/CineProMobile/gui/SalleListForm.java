/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CineProMobile.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import edu.CineProMobile.services.ServiceSalle;
import edu.CineProMobile.entites.Salle;

/**
 *
 * @author user
 */
public class SalleListForm  extends Form {
    Form current;

    public SalleListForm(Form previous) {
    current=this;
        setTitle("Liste des salles");
        for(Salle s : ServiceSalle.getInstance().getAllSalles() ){
            Label lb = new Label(s.getNomS());
            lb.addPointerReleasedListener((e)->{
          
                    Form salleDetails = new Form(s.getNomS(),BoxLayout.y());
            }
            );
        }
    }

}
