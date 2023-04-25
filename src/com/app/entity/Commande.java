package com.app.entity;

public class Commande {

    private int id_Commande;
    private String Date_Commande ;
    private double HT;
    private double TTC;
    private double TVA;
    private int Id_Client ;


    public Commande(int id_Commande, String date_Commande, double HT, double TTC, double TVA, int id_Client) {
        this.id_Commande = id_Commande;
        Date_Commande = date_Commande;
        this.HT = HT;
        this.TTC = TTC;
        this.TVA = TVA;
        Id_Client = id_Client;
    }

    public int getId_Commande() {
        return id_Commande;
    }

    public void setId_Commande(int id_Commande) {
        this.id_Commande = id_Commande;
    }

    public String getDate_Commande() {
        return Date_Commande;
    }

    public void setDate_Commande(String Date_Commande) {
        this.Date_Commande = Date_Commande;
    }

    public double getHT() {
        return HT;
    }

    public void setHT(double HT) {
        this.HT = HT;
    }

    public double getTTC() {
        return TTC;
    }

    public void setTTC(double TTC) {
        this.TTC = TTC;
    }

    public double getTVA() {
        return TVA;
    }

    public void setTVA(double TVA) {
        this.TVA = TVA;
    }

    public int getId_Client() {
        return Id_Client;
    }

    public void setId_Client(int Id_Client) {
        this.Id_Client = Id_Client;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_Commande=" + id_Commande + ", Date_Commande=" + Date_Commande + ", HT=" + HT + ", TTC=" + TTC + ", TVA=" + TVA + ", Id_Client=" + Id_Client + '}';
    }



}
