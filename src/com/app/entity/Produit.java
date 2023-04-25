package com.app.entity;

public class Produit {
    private int id_produit;
    private int prix;
    private int quantite;
    private String description;

    public Produit() {
    }

    public Produit(int id_produit, int prix, int quantite, String description) {
        this.id_produit = id_produit;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ID Produit :" + id_produit + ", Description : " + description +" , Prix Hors Taxe : " + prix + " €.";
    }

}

