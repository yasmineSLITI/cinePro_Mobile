/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CineProMobile.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import edu.CineProMabile.utils.Statics;
import edu.CineProMobile.entites.Evenement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author user
 */
public class ServiceEvenement {

    public ArrayList<Evenement> events;

    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    public boolean addEvenement(Evenement t,int id) {
        System.out.println(t);
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/demanderEvent/reaJSON/"+id+"/new";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("nomev", t.getNomev());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public Evenement parseEvenementRef(String jsonText) {
        Evenement t = new Evenement();
        try {

            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventListJson.get("root");
            for (Map<String, Object> obj : list) {

                
                if (obj.get("etat").toString().equals("Refusé")) {
                    t.setIdev(Float.parseFloat(obj.get("idev").toString()));
                    t.setNomev(obj.get("nomev").toString());
                    t.setEtat(obj.get("etat").toString());
                    t.setDescription(obj.get("description").toString());
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }
    public Evenement parseEvenementAccept(String jsonText) {
        Evenement t = new Evenement();
        try {

            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventListJson.get("root");
            for (Map<String, Object> obj : list) {

                
                if (obj.get("etat").toString().equals("Accepté")) {
                    t.setIdev(Float.parseFloat(obj.get("idev").toString()));
                    t.setNomev(obj.get("nomev").toString());
                    t.setEtat(obj.get("etat").toString());
                    t.setDescription(obj.get("description").toString());
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }
    public ArrayList<Evenement> parseEvenements(String jsonText) {
        try {

            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventListJson.get("root");
            for (Map<String, Object> obj : list) {

                Evenement t = new Evenement();
                if (obj.get("etat").toString().equals("Accepté")) {
                    t.setIdev(Float.parseFloat(obj.get("idev").toString()));
                    t.setNomev(obj.get("nomev").toString());
                    t.setEtat(obj.get("etat").toString());
                    t.setDescription(obj.get("description").toString());
                    events.add(t);
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return events;
    }

    

    public ArrayList<Evenement> getAllEvenements() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "afficherEventJSON";
        System.out.println("===>" + url);
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        InfiniteProgress prg = new InfiniteProgress();
        Dialog diag = prg.showInfiniteBlocking();
        req.setDisposeOnCompletion(diag);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

    ////////////// demande d'event en attente d'acceptation //////////////////////////
    public ArrayList<Evenement> parseEvenementsEnAttente(String jsonText) {
        try {

            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> obj : list) {

                Evenement t = new Evenement();
                if (obj.get("etat").toString().equals("En attente")) {
                    t.setIdev(Float.parseFloat(obj.get("idev").toString()));
                    t.setNomev(obj.get("nomev").toString());
                    t.setEtat(obj.get("etat").toString());
                    t.setDescription(obj.get("description").toString());
                    events.add(t);
                }

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return events;
    }

    public ArrayList<Evenement> getAllEvenementsEnAttente() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "afficherEventJSON";
        System.out.println("===>" + url);
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvenementsEnAttente(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        InfiniteProgress prg = new InfiniteProgress();
        Dialog diag = prg.showInfiniteBlocking();
        req.setDisposeOnCompletion(diag);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
public Evenement accepterEvent(int id){
    Evenement t = new Evenement();
    String url =Statics.BASE_URL+"refuserevenementJSON/"+id;
    req.setUrl(url);
//    req.addResponseListener(new ActionListener<NetworkEvent>() {
//        @Override
//        public void actionPerformed(NetworkEvent evt) {
//            t = parseEvenementAccept(new String(req.getResponseData()));
//            req.removeResponseListener(this);
//        }
//    });
    InfiniteProgress prg = new InfiniteProgress();
        Dialog diag = prg.showInfiniteBlocking();
        req.setDisposeOnCompletion(diag);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return t;
}

}
