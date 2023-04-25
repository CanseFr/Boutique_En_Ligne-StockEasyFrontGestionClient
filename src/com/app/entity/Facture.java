package com.app.entity;

//______________________________________________________________________________________________________________________
//             !!!!!!!!!!!!! ATTENTION FACTURE NE POSSEDE PAS DE TYPE DE PAIEMENT EN BASE DE DONNEES !!!!!!!!!!!!
//______________________________________________________________________________________________________________________

public class Facture {

    private int Id_Facture ;
    private String Date_Facturation ;
    private double Total_HT ;
    private double Total_TVA ;
    private double Total_TTC ;
    private int Id_Commande ;
    //private String typePaiement ; !!

    public Facture(int id_Facture, String date_Facturation, double total_HT, double total_TVA, double total_TTC, int id_Commande ) {
        Id_Facture = id_Facture;
        Date_Facturation = date_Facturation;
        Total_HT = total_HT;
        Total_TVA = total_TVA;
        Total_TTC = total_TTC;
        Id_Commande = id_Commande;
    }

    public int getId_Facture() {
        return Id_Facture;
    }

    public void setId_Facture(int Id_Facture) {
        this.Id_Facture = Id_Facture;
    }

    public String getDate_Facturation() {
        return Date_Facturation;
    }

    public void setDate_Facturation(String Date_Facturation) {
        this.Date_Facturation = Date_Facturation;
    }

    public double getTotal_HT() {
        return Total_HT;
    }

    public void setTotal_HT(double Total_HT) {
        this.Total_HT = Total_HT;
    }

    public double getTotal_TVA() {
        return Total_TVA;
    }

    public void setTotal_TVA(double Total_TVA) {
        this.Total_TVA = Total_TVA;
    }

    public int getId_Commande() {
        return Id_Commande;
    }

    public void setId_Commande(int Id_Commande) {
        this.Id_Commande = Id_Commande;
    }

    public double getTotal_TTC() {
        return Total_TTC;
    }

    public void setTotal_TTC(double Total_TTC) {
        this.Total_TTC = Total_TTC;
    }

    @Override
    public String toString() {
        return "Facture{" + "Id_Facture=" + Id_Facture + ", Date_Facturation=" + Date_Facturation + ", Total_HT=" + Total_HT + ", Total_TVA=" + Total_TVA + ", Total_TTC=" + Total_TTC + ", Id_Commande=" + Id_Commande + '}';
    }

}