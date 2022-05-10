/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author Asus
 */
public class ServiceProducts {

    public ArrayList<Produit> products;
    public Produit produit;
    public String checkLike;

    public static ServiceProducts instance = null;
    public boolean resultOK;
    public String produitEnStock_Count;
    public String produitOutOfStock_Count;
    public String produitMonthlySales_Count;
    public String[] followings;
    private ConnectionRequest req;

    private ServiceProducts() {
        req = new ConnectionRequest();
    }

    public static ServiceProducts getInstance() {
        if (instance == null) {
            instance = new ServiceProducts();
        }
        return instance;
    }

    public ArrayList<Produit> parseProducts(String jsonText) {
        try {
            products = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> gamesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) gamesListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Produit g = new Produit();
                float id = Float.parseFloat(obj.get("idproduit").toString());
                float quantiteStock = Float.parseFloat(obj.get("quantiteenstock").toString());
                double prixAchatUnit = (double) Float.parseFloat(obj.get("prixachatunit").toString());
                double prixVenteUnit = (double) Float.parseFloat(obj.get("prixventeunit").toString());
                g.setIDProduit((int) id);
                g.setDesignation(obj.get("designation").toString());
                g.setImage(obj.get("image").toString());
                g.setDescription(obj.get("description").toString());
                g.setQuantiteEnStock((int) quantiteStock);
                g.setPrixAchatUnit((double) prixAchatUnit);
                g.setPrixVenteUnit((double) prixVenteUnit);

                //Ajouter la tâche extraite de la réponse Json à la liste
                products.add(g);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return products;
    }

    public ArrayList<Produit> getAllProducts() {
        String url = Statics.BASE_URL + "/products";
        System.out.println(url);
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProducts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }

    public Boolean addProduct(Produit c) {

        String url = Statics.BASE_URL + "/product/addProduct?designation=" + c.getDesignation() + "&description=" + c.getDescription()
                + "&image=" + c.getImage() + "&prixachatunit=" + c.getPrixAchatUnit() + "&prixventeunit=" + c.getPrixVenteUnit() + "&quantiteenstock=" + c.getQuantiteEnStock();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        ToastBar.showMessage(" produit est ajoutée avec succès", FontImage.MATERIAL_ACCESS_TIME);

        return resultOK;

    }

    public Boolean EditProduct(Produit c) {
        System.out.println("this :" + c);
        String url = Statics.BASE_URL + "/product/updateProduct/" + c.getIDProduit() + "?designation=" + c.getDesignation() + "&description=" + c.getDescription()
                + "&image=" + c.getImage() + "&prixachatunit=" + c.getPrixAchatUnit() + "&prixventeunit=" + c.getPrixVenteUnit() + "&quantiteenstock=" + c.getQuantiteEnStock();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        ToastBar.showMessage(" produit est modifié", FontImage.MATERIAL_ACCESS_TIME);

        return resultOK;

    }

    public void deleteProduct(int id) {
        String url = Statics.BASE_URL + "/product/delete/" + id;
        req.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(req);
        ToastBar.showMessage(" produit est supprimé", FontImage.MATERIAL_ACCESS_TIME);

    }

    public String isLiked(String idproduit, String idclient) {
        String url = Statics.BASE_URL + "/produit/isLiked/" + idproduit + "/" + idclient;
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    checkLike = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return checkLike;
    }

    public void unFollowProduct(String idproduit, String idclient) {
        String url = Statics.BASE_URL + "/product/unfollow/" + idproduit + "/" + idclient;
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        ToastBar.showMessage(" produit est retirée avec succès de votre wishList", FontImage.MATERIAL_ACCESS_TIME);

    }

    public void followProduct(String idproduit, String idclient) {
        String url = Statics.BASE_URL + "/product/follow/" + idproduit + "/" + idclient;
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        ToastBar.showMessage(" produit est ajoutée avec succès à votre wishList", FontImage.MATERIAL_ACCESS_TIME);

    }

    public ArrayList<Produit> getAllFollowedProducts() {
        String url = Statics.BASE_URL + "/product/LikedProducts";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProducts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }

    public String InStockProduit() {
        String url = Statics.BASE_URL + "/stats/InStockProducts";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitEnStock_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitEnStock_Count;
    }

    public String OutOfStockProduit() {
        String url = Statics.BASE_URL + "/stats/OutOfStockProducts";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitOutOfStock_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitOutOfStock_Count;
    }

    public String JanuarySales() {
        String url = Statics.BASE_URL + "/stats/JanuarySales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

    public String FebuarySales() {
        String url = Statics.BASE_URL + "/stats/FebuarySales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

    public String MarchSales() {
        String url = Statics.BASE_URL + "/stats/MarchSales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

    public String AprilSales() {
        String url = Statics.BASE_URL + "/stats/AprilSales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

    public String MaySales() {
        String url = Statics.BASE_URL + "/stats/MaySales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

    public String JuneSales() {
        String url = Statics.BASE_URL + "/stats/JuneSales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

    public String JulySales() {
        String url = Statics.BASE_URL + "/stats/JulySales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

    public String AugustSales() {
        String url = Statics.BASE_URL + "/stats/AugustSales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

    public String septemberSales() {
        String url = Statics.BASE_URL + "/stats/SeptemberSales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

    public String OctoberSales() {
        String url = Statics.BASE_URL + "/stats/OctoberSales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

    public String novemberSales() {
        String url = Statics.BASE_URL + "/stats/novemberSales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

    public String decemberSales() {
        String url = Statics.BASE_URL + "/stats/decemberSales";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                    produitMonthlySales_Count = new String(req.getResponseData());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produitMonthlySales_Count;
    }

}
