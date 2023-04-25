package com.app.entity;

public class LigneCommande {

    private static int cptId = 0;
    // fair une requete poir determiner la valeur du derniere id produit en base de donnée !!

    private int Id_Ligne_Commande = 0;
    private int Quantite_commande ;
    private double Prix_total_Ht;
    private int Id_Produit ;
    private int Id_Commande;

    public LigneCommande() {
        this.Id_Ligne_Commande = cptId++;
    }

    public LigneCommande(int Id_Ligne_Commande,int Quantite_commande, double Prix_total_Ht, int Id_Produit, int Id_Commande) {
        this.Id_Ligne_Commande = Id_Ligne_Commande;
        this.Quantite_commande = Quantite_commande;
        this.Prix_total_Ht = Prix_total_Ht;
        this.Id_Produit = Id_Produit;
        this.Id_Commande = Id_Commande;
    }

    public int getId_Ligne_Commande() {
        return Id_Ligne_Commande;
    }

    public void setId_Ligne_Commande(int Id_Ligne_Commande) {
        this.Id_Ligne_Commande = Id_Ligne_Commande;
    }

    public int getQuantite_commande() {
        return Quantite_commande;
    }

    public void setQuantite_commande(int Quantite_commande) {
        this.Quantite_commande = Quantite_commande;
    }

    public double getPrix_total_Ht() {
        return Prix_total_Ht;
    }

    public void setPrix_total_Ht(double Prix_total_Ht) {
        this.Prix_total_Ht = Prix_total_Ht;
    }

    public int getId_Produit() {
        return Id_Produit;
    }

    public void setId_Produit(int Id_Produit) {
        this.Id_Produit = Id_Produit;
    }

    public int getId_Commande() {
        return Id_Commande;
    }

    public void setId_Commande(int Id_Commande) {
        this.Id_Commande = Id_Commande;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "Id_Ligne_Commande=" + Id_Ligne_Commande + ", Quantite_commande=" + Quantite_commande + ", Prix_total_Ht=" + Prix_total_Ht + ", Id_Produit=" + Id_Produit + ", Id_Commande=" + Id_Commande + '}';
    }

}
