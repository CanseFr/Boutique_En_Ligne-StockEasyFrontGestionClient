package com.app.entity;

public class Client {
    private int Id_Client ;
    private String User_Name ;
    private String Password;
    private String Mail ;
    private String Adresse ;
    private String CP ;
    private String Ville;

    // Vide
    public Client() {
    }

    // ListAllClt
    public Client(int id_Client, String user_Name, String mail, String adresse, String CP, String ville) {
        Id_Client = id_Client;
        User_Name = user_Name;
        Mail = mail;
        Adresse = adresse;
        this.CP = CP;
        Ville = ville;
    }

    // All
    public Client(int Id_Client, String User_Name, String Password, String Mail, String Adresse, String CP, String Ville) {
        this.Id_Client = Id_Client;
        this.User_Name = User_Name;
        this.Password = Password;
        this.Mail = Mail;
        this.Adresse = Adresse;
        this.CP = CP;
        this.Ville = Ville;
    }

    public int getId_Client() {
        return Id_Client;
    }

    public void setId_Client(int Id_Client) {
        this.Id_Client = Id_Client;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }



}
