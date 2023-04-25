package com.app.entity;

public class Commerciaux {
    int Id_User;
    String User_Name ;
    String Password ;
    String Mail ;
    String Nom ;
    String Prenom ;

    public Commerciaux() {
    }

    // Pour la liste les commerciaux   1 UPDATE ATTENTIONN A INTEGER VALUE OF
    public Commerciaux(int id_User, String user_Name, String mail, String nom, String prenom) {
        Id_User = id_User;
        User_Name = user_Name;
        Mail = mail;
        Nom = nom;
        Prenom = prenom;
    }
    // Pour creer un commercial
    public Commerciaux(String user_Name, String password, String mail, String nom, String prenom) {
        User_Name = user_Name;
        Password = password;
        Mail = mail;
        Nom = nom;
        Prenom = prenom;
    }


    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int id_User) {
        Id_User = id_User;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id_User=" + Id_User +
                ", User_Name='" + User_Name + '\'' +
                ", Password='" + Password + '\'' +
                ", Mail='" + Mail + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                '}';
    }


    public String toStringUserInfo() {
        return "User{" +
                "Id_User=" + Id_User +
                ", User_Name='" + User_Name + '\'' +
                ", Mail='" + Mail + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                '}';
    }


}
