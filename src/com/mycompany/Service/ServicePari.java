/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.FichePari;
import com.mycompany.Entite.Match;
import com.mycompany.Entite.Pari;
import com.mycompany.Utilitaire.Parser;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author skanderbejaoui
 */
public class ServicePari {
private static boolean test = false;
    public static ArrayList<Match> getList() {
        ArrayList<Match> listMatchs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/Match2018/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> matchs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) matchs.get("root");
                    for (Map<String, Object> obj : list) {
                        Map<String, Object> Equipe1 = (Map<String, Object>) obj.get("idEquipe1");
                        Map<String, Object> Equipe2 = (Map<String, Object>) obj.get("idEquipe2");
                        Map<String, Object> Date = (Map<String, Object>) obj.get("date");
                        /* DateFormat date = new SimpleDateFormat();
                        date = (DateFormat) Date.get("timestamp");*/

                        Equipe E1 = new Equipe();
                        Equipe E2 = new Equipe();
                        Match match = new Match();
                        float id = Float.parseFloat(obj.get("id").toString());
                        match.setId((int) id);
                        match.setCote_eq1(Float.parseFloat(obj.get("coteEq1").toString()));
                        match.setCote_eq2(Float.parseFloat(obj.get("coteEq2").toString()));
                        match.setCote_nul(Float.parseFloat(obj.get("coteNul").toString()));
                        E1.setNom(Equipe1.get("nom").toString());
                        E1.setDrapeau(Equipe1.get("drapeau").toString());
                        E2.setDrapeau(Equipe2.get("drapeau").toString());
                        E2.setNom(Equipe2.get("nom").toString());
                        match.setE1(E1);
                        match.setE2(E2);
                        // task.setNom(obj.get("name").toString());
                        listMatchs.add(match);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMatchs;
    }

    public static List<Pari> getListPromosport() {
        ArrayList<Pari> listparis = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/Pari/MobilePromosport");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> paris = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) paris.get("root");
                    for (Map<String, Object> obj : list) {
                        // Map<String, Object> Equipe1 = (Map<String, Object>) obj.get("idEquipe1");
                        // Map<String, Object> Equipe2 = (Map<String, Object>) obj.get("idEquipe2");
                        // Map<String, Object> Date = (Map<String, Object>) obj.get("date");

                        /* DateFormat date = new SimpleDateFormat();
                        date = (DateFormat) Date.get("timestamp");*/
                        // Equipe E1 = new Equipe();
                        //Equipe E2 = new Equipe();
                        Pari p = new Pari();
                        float id = Float.parseFloat(obj.get("id").toString());
                        p.setId((int) id);

                        Match m = Parser.toMatch(obj.get("idMatch"));
                        p.setM(m);

                        listparis.add(p);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return listparis;
    }

    public static Map<Date, List<Match>> getAll() {
        Map<Date, List<Match>> matchs = new TreeMap<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/Pari/MobileIndex");
        con.addResponseListener((e) -> {
            try {
                JSONParser jsonp = new JSONParser();
                Map<String, Object> jsonmatchs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                for (Map.Entry<String, Object> entry : jsonmatchs.entrySet()) {

                    Date datematch = toDate(entry.getKey());

                    List<Match> listmatch = new ArrayList<>();
                    List<Map<String, Object>> list = (List<Map<String, Object>>) entry.getValue();
                    for (Map<String, Object> map : list) {
                        Match m = Parser.toMatch(map, datematch);
                        listmatch.add(m);
                    }

                    matchs.put(datematch, listmatch);
                }
            } catch (IOException ex) {

            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return matchs;
    }

    public static Date toDate(String s) {
        try {

            DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
            Date datematch = formatter.parse(s);

            return datematch;
        } catch (java.text.ParseException ex) {

        }
        return null;
    }

    public void AjoutPari(Match match, FichePari fp, float mise, float gain, float cote, String resultat) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/Match2018/pari/" + match.getId() + "/" + fp.getId() + "/" + mise + "/" + gain + "/" + cote + "/" + resultat);
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public static List<FichePari> getListHistoriquePari(int idUser) {
        ArrayList<FichePari> listficheparis = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/Pari/MobileHistorique/" + idUser);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> ficheparis = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) ficheparis.get("root");
                    for (Map<String, Object> obj : list) {
                        // Map<String, Object> Equipe1 = (Map<String, Object>) obj.get("idEquipe1");
                        // Map<String, Object> Equipe2 = (Map<String, Object>) obj.get("idEquipe2");
                        // Map<String, Object> Date = (Map<String, Object>) obj.get("date");

                        /* DateFormat date = new SimpleDateFormat();
                        date = (DateFormat) Date.get("timestamp");*/
                        // Equipe E1 = new Equipe();
                        //Equipe E2 = new Equipe();
                        FichePari fp = new FichePari();
                        float id = Float.parseFloat(obj.get("id").toString());
                        float type = Float.parseFloat((obj.get("type").toString()));
                        fp.setId((int) id);
                        fp.setCote_total(Float.parseFloat(obj.get("cotetotal").toString()));
                        fp.setMisetotal(Float.parseFloat(obj.get("misetotal").toString()));
                        fp.setGain(Float.parseFloat(obj.get("gain").toString()));
                        fp.setType((int) type);
                        fp.setEtat(FichePari.EtatFiche.valueOf(obj.get("etat").toString()));

                        listficheparis.add(fp);

                    }
                } catch (IOException ex) {
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listficheparis;
    }

    public static List<Pari> getPariparFiche(int idFichePari) {
        ArrayList<Pari> listparis = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/Pari/MobileHistorique/Client/" + idFichePari);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> paris = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) paris.get("root");
                    for (Map<String, Object> obj : list) {
                        // Map<String, Object> Equipe1 = (Map<String, Object>) obj.get("idEquipe1");
                        // Map<String, Object> Equipe2 = (Map<String, Object>) obj.get("idEquipe2");
                        // Map<String, Object> Date = (Map<String, Object>) obj.get("date");

                        /* DateFormat date = new SimpleDateFormat();
                        date = (DateFormat) Date.get("timestamp");*/
                        // Equipe E1 = new Equipe();
                        //Equipe E2 = new Equipe();
                        Pari p = new Pari();
                        float id = Float.parseFloat(obj.get("id").toString());
                        p.setId((int) id);

                        Match m = Parser.toMatch(obj.get("idMatch"));
                        p.setM(m);
                        p.setResultat(Pari.ResultatPari.valueOf(obj.get("resultat").toString()));
                        listparis.add(p);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return listparis;
    }

    public static boolean GagnantPari() {
       test = false;
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/RS2018/web/Pari/Mobile/gagnant");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> paris = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    if(paris.get("Test").toString()=="true"){
                        test = true;
                    }
                  
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
            return test ;
        }
    
}
