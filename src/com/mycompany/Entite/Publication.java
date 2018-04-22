/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ghassen
 */
public class Publication {

    /**
     * enum type de publication
     */
    public enum Type {

        /**
         * type article
         */
        article,
        /**
         * type galerie
         */
        galerie
    }
    public enum Confirme{oui,non}
    private Confirme confirme;

    public Confirme getConfirme() {
        return confirme;
    }

    public void setConfirme(Confirme confirme) {
        this.confirme = confirme;
    }
    
    private int id, liked, disliked;
    private String titre, lien, description;
    private Date date_creation;
    private Type type;
    private User user;
    private List<Commentaire> commentaires;

    public Publication(int id, int liked, int disliked, String titre, String lien, String description, Date date_creation, Type type, User user, List<Commentaire> commentaires) {
        this.id = id;
        this.liked = liked;
        this.disliked = disliked;
        this.titre = titre;
        this.lien = lien;
        this.description = description;
        this.date_creation = date_creation;
        this.type = type;
        this.user = user;
        this.commentaires = commentaires;
    }

    public Publication(int liked, int disliked, String titre, String lien, String description, Date date_creation, Type type, User user, List<Commentaire> commentaires) {
        this.liked = liked;
        this.disliked = disliked;
        this.titre = titre;
        this.lien = lien;
        this.description = description;
        this.date_creation = date_creation;
        this.type = type;
        this.user = user;
        this.commentaires = commentaires;
    }

    public Publication() {
        this.commentaires = new ArrayList<>();
        this.user = new User();
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
     * @return nombre des like d'une publication
     */
    public int getLiked() {
        return liked;
    }

    /**
     *
     * @param liked
     */
    public void setLiked(int liked) {
        this.liked = liked;
    }

    /**
     *
     * @return nombre des dislike
     */
    public int getDisliked() {
        return disliked;
    }

    /**
     *
     * @param disliked
     */
    public void setDisliked(int disliked) {
        this.disliked = disliked;
    }

    /**
     *
     * @return titre de la publication
     */
    public String getTitre() {
        return titre;
    }

    /**
     *
     * @param titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     *
     * @return lien de la publication
     */
    public String getLien() {
        return lien;
    }

    /**
     *
     * @param lien
     */
    public void setLien(String lien) {
        this.lien = lien;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return date dreation
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
     * @return type de la publication
     */
    public Type getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", liked=" + liked + ", disliked=" + disliked + ", titre=" + titre + ", lien=" + lien + ", description=" + description + ", date_creation=" + date_creation + ", type=" + type + ", user=" + user + '}';
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
    
    

}
