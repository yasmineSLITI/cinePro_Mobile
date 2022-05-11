/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CineProMobile.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import edu.CineProMabile.utils.Statics;
import edu.CineProMobile.entites.Salle;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceSalle {

    public ArrayList<Salle> salle = new ArrayList<>();

    public static ServiceSalle instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceSalle() {
        req = new ConnectionRequest();
    }

    public static ServiceSalle getInstance() {
        if (instance == null) {
            instance = new ServiceSalle();
        }
        return instance;
    }

    public boolean addSalle(Salle t) {
        System.out.println(t);
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + 
        //"&status=" + t.getStatus();
        String url = Statics.BASE_URL + "ajouterSalleJSON/new?capacite=" + t.getCapacite() + "&datedemaintenance=" + t.getDateDerniereMaintenance() + "&enmaintenance=false&nomsalle=" + t.getNomS() + "&disponible=Disponible";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("nomsalle", t.getNomS());
        req.addArgument("capacite", Float.toString(t.getCapacite()));
        req.addArgument("datedemaintenance", t.getDateDerniereMaintenance());
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

    public ArrayList<Salle> parseSalle(String jsonText) {
        String res = jsonText;

        try {

            salle = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> salleListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) salleListJson.get("root");
            for (Map<String, Object> obj : list) {
                Salle t = new Salle();
                t.setNomS(obj.get("nomsalle").toString());
                String dd = obj.get("datedemaintenance").toString().substring(6, 17);
                t.setDateDerniereMaintenance(dd);
                if (obj.get("enmaintenance").toString() == "false") {
                    t.setEnMaintenance("Maintenue");
                } else {
                    t.setEnMaintenance("En maintenance");
                }
                t.setDispo(obj.get("disponible").toString());
                t.setId(Float.parseFloat(obj.get("idsa").toString()));
                t.setCapacité(Float.parseFloat(obj.get("capacite").toString()));
                salle.add(t);
            }

        } catch (IOException ex) {
            System.out.println("ERROR MSG !!!!!!!!! " + ex.getMessage());
        }
        return salle;
    }

    public ArrayList<Salle> getAllSalles() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "salleJSON";
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(url);

        System.out.println("" + url);;
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                salle = parseSalle(new String(req.getResponseData()));
                System.out.println("Salle 116 " + salle);
                req.removeResponseListener(this);

            }
        });
        InfiniteProgress prg = new InfiniteProgress();
        Dialog diag = prg.showInfiniteBlocking();
        req.setDisposeOnCompletion(diag);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return salle;

    }

    public ArrayList<Salle> parseSalleDispo(String jsonText) {
        String res = jsonText;

        try {

            salle = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> salleListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) salleListJson.get("root");
            for (Map<String, Object> obj : list) {
                Salle t = new Salle();
                if (obj.get("disponible").toString() == "Disponible" && obj.get("enmaintenance").toString().equals("false")) {
                    t.setNomS(obj.get("nomsalle").toString());
                    String dd = obj.get("datedemaintenance").toString().substring(6, 17);
                    t.setDateDerniereMaintenance(dd);
                    t.setEnMaintenance("Maintenue");
                    t.setDispo(obj.get("disponible").toString());
                    t.setId(Float.parseFloat(obj.get("idsa").toString()));
                    t.setCapacité(Float.parseFloat(obj.get("capacite").toString()));
                    salle.add(t);
                }

            }

        } catch (IOException ex) {
            System.out.println("ERROR MSG !!!!!!!!! " + ex.getMessage());
        }
        return salle;
    }

    public ArrayList<Salle> getAllSallesDispo() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "salleJSON";
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(url);

        System.out.println("" + url);;
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                salle = parseSalle(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        InfiniteProgress prg = new InfiniteProgress();
        Dialog diag = prg.showInfiniteBlocking();
        req.setDisposeOnCompletion(diag);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return salle;

    }

    public ArrayList<Salle> parseSalleMain(String jsonText) {
        String res = jsonText;

        try {

            salle = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> salleListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) salleListJson.get("root");
            for (Map<String, Object> obj : list) {
                Salle t = new Salle();
                if (obj.get("enmaintenance").toString() == "true") {
                    t.setEnMaintenance("En maintenue");
                    t.setNomS(obj.get("nomsalle").toString());
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+t.getNomS());
                    String dd = obj.get("datedemaintenance").toString().substring(6, 17);
                    t.setDateDerniereMaintenance(dd);

                    t.setDispo(obj.get("disponible").toString());
                    t.setId(Float.parseFloat(obj.get("idsa").toString()));
                    t.setCapacité(Float.parseFloat(obj.get("capacite").toString()));
                    salle.add(t);
                }
            }

        } catch (IOException ex) {
            System.out.println("ERROR MSG !!!!!!!!! " + ex.getMessage());
        }
        return salle;
    }

    public ArrayList<Salle> getAllSallesMain() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "salleJSON";
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setUrl(url);

        System.out.println("" + url);;
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                salle = parseSalleMain(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        InfiniteProgress prg = new InfiniteProgress();
        Dialog diag = prg.showInfiniteBlocking();
        req.setDisposeOnCompletion(diag);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return salle;

    }

}
