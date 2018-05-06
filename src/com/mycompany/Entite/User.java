/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ghassen
 */
public class User {

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", etat=" + etat + ", type=" + type + ", date_creation=" + date_creation + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", email=" + email + ", mdp=" + mdp + ", image=" + image + ", role=" + role + ", connecte=" + connecte + ", publications=" + publications + '}';
    }

    /**
     * les roles des utilisateurs
     */
    public enum Role {
        

        /**
         * role admin
         */
        admin,
        /**
         * role moderateur
         */
        moderateur,
        /**
         * role journaliste
         */
        journaliste,
        /**
         * role membre
         */
        membre
    }

    /**
     * user connecte
     */
    public enum Connecte {

        /**
         *
         */
        ON,
        /**
         *
         */
        OFF
    }
    private int id, etat, type;
    private Date date_creation;
    private String nom, prenom, username, email, mdp, image, confirmkey;
    private float jeton;
    private Role role;
    private Connecte connecte;
    private List<Publication> publications;
    private List<Commentaire> commentaires;
    private List<FichePari> fp;

    public List<FichePari> getFp() {
        return fp;
    }

    public void setFp(List<FichePari> fp) {
        this.fp = fp;
    }
    /**
     * Constructeur par defaut
     */
    public User() {
        this.commentaires = new ArrayList<>();
        this.publications = new ArrayList<>();
    }

    public String getConfirmkey() {
        return confirmkey;
    }

    public void setConfirmkey(String confirmkey) {
        this.confirmkey = confirmkey;
    }

    public float getJeton() {
        return jeton;
    }

    public void setJeton(float jeton) {
        this.jeton = jeton;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    /**
     *
     * @return liste des publications d'un utilisateur
     */
    public List<Publication> getPublications() {
        return publications;
    }

    /**
     *
     * @param publications
     */
    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    /**
     *
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return etat
     */
    public int getEtat() {
        return etat;
    }

    /**
     *
     * @param etat
     */
    public void setEtat(int etat) {
        this.etat = etat;
    }

    /**
     *
     * @return date creation du compte
     */
    public Date getDate_creation() {
        return date_creation;
    }

    /**
     *
     * @param date_creation
     */
    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    /**
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return mot de passe
     */
    public String getMdp() {
        return mdp;
    }

    /**
     *
     * @param mdp
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     *
     * @return lien image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     *
     * @param role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     *
     * @return connecte
     */
    public Connecte getConnecte() {
        return connecte;
    }

    /**
     *
     * @param connecte
     */
    public void setConnecte(Connecte connecte) {
        this.connecte = connecte;
    }

    /*public static List<String> generateUsernameSuggestions(String username) {
        List<String> tmp = new ArrayList<>();
        ServiceUser su = new ServiceUser();
        Random randomGenerator = new Random();
        while (tmp.size() < 5) {
            int randomInt = randomGenerator.nextInt(100);
            String usernamegenerated = username + randomInt;
            User u = su.retrieveUsername(usernamegenerated);
            if (u == null) {
                tmp.add(usernamegenerated);
            }
        }
        return tmp;
    }*/

    @Override
    public int hashCode() {
        return 5 ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
