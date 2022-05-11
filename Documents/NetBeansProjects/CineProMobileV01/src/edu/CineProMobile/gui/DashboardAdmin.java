package edu.CineProMabile.gui;


import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import edu.CineProMobile.gui.AjouterSalleForm;
import edu.CineProMobile.gui.EvenetListForm;
import edu.CineProMobile.gui.ListSalleForm;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
public class MyApplication {
 */
public class DashboardAdmin extends Form {
    Form current;
    public DashboardAdmin() {
        current=this; //Back 
        setTitle("Dashboard");
        setLayout(new FlowLayout(CENTER,CENTER));
        getToolbar().addCommandToSideMenu("Dashbord",null, e-> current.showBack());
        getToolbar().addCommandToSideMenu("Salles",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new ListSalleForm(current).show(); 
            }
        });
        getToolbar().addCommandToSideMenu("Evenements",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new EvenetListForm(current).show(); 
            }
        });
        getToolbar().addCommandToSideMenu("Demande de sponsoring",null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new ListSalleForm(current).show(); 
            }
        });
        
        
        add(new Label("Welcome admin ! "));
        
        
    }
}
