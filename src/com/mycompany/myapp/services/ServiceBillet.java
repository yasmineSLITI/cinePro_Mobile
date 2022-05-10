/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Billet;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author Asus
 */
public class ServiceBillet {

    public static ServiceBillet instance = null;
    private ConnectionRequest req;
    public boolean resultOK;
    public String prices;

    private ServiceBillet() {
        req = new ConnectionRequest();
    }

    public static ServiceBillet getInstance() {
        if (instance == null) {
            instance = new ServiceBillet();
        }
        return instance;
    }

    public Boolean addBillet(Billet B) {

        String url = Statics.BASE_URL + "/billet/addBillet?nbPlace=" + B.getNb_place() + "&categoriebillet=" + B.getCategorieBillet()
                + "&idReservation=" + B.getIdReservation() + "&idClient=" + B.getIdClient();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        ToastBar.showMessage(" votre billet est reservée avec succès", FontImage.MATERIAL_ACCESS_TIME);

        return resultOK;
    }


    public String prixInitialBillet(int NbPlace,String categoriebillet) {
        String url = Statics.BASE_URL + "/billet/prixInitialBillet/"+NbPlace+"/"+categoriebillet;
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    prices = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prices;
    }
    
       public String remise() {
        String url = Statics.BASE_URL + "/billet/remise";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    prices = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prices;
    }

}
