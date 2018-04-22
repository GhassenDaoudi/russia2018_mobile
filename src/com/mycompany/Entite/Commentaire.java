/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.Date;


/**
 *
 * @author Ghassen
 */
public class Commentaire {
    public enum LikeDislike{
        like,dislike,none
    }
    private int id;
    private String description;
    private Date date_creation;
    private User user;
    private Publication publication;
    private LikeDislike etat;
    
    public Commentaire(String description, Date date_creation, User user, Publication publication, LikeDislike etat) {
        this.description = description;
        this.date_creation = date_creation;
        this.user = user;
        this.publication = publication;
        this.etat = etat;
    }
    

    public LikeDislike getEtat() {
        return etat;
    }

    public void setEtat(LikeDislike etat) {
        this.etat = etat;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Commentaire() {
        this.user = new User();
        this.publication = new Publication();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", description=" + description + ", date_creation=" + date_creation + ", etat=" + etat + '}';
    }

    

}
