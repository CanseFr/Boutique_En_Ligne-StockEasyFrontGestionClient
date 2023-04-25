package com.app.dao;

import com.app.entity.Commande;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepositoryCommande {
    static Connection connection;
    public RepositoryCommande() throws SQLException {
        connection = DAOConnectionManager.getConnection();
    }
    public List<Commande> listAllCommande() throws SQLException {

        List<Commande> commandeList = new ArrayList<>();

        String queryListAllCommande = "SELECT * FROM Commande ;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryListAllCommande);

        while (resultSet.next()){

            int Id_Commande = resultSet.getInt("Id_Commande");
            String Date_Commande = resultSet.getString("Date_Commande");
            double HT = resultSet.getDouble("HT");
            double TTC = resultSet.getDouble("TTC");
            double TVA = resultSet.getDouble("TVA");
            int Id_Client =  resultSet.getInt("Id_Client");

            Commande commande = new Commande(Id_Commande, Date_Commande, HT, TTC, TVA, Id_Client);
            commandeList.add(commande);
        }

        return commandeList;
    }

    public List<Commande> listAllCommandeByIdClientContains(int idClient) throws SQLException{

        List<Commande> commandeList = new ArrayList<>();

        String queryListAllCommandeContains = "SELECT * FROM Commande WHERE Id_Client LIKE '"+ idClient +"' ; " ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryListAllCommandeContains);

        while (resultSet.next()){
            int Id_Commande = resultSet.getInt("Id_Commande");
            String Date_Commande = resultSet.getString("Date_Commande");
            double HT = resultSet.getDouble("HT");
            double TTC = resultSet.getDouble("TTC");
            double TVA = resultSet.getDouble("TVA");
            int Id_Client =  resultSet.getInt("Id_Client");

            Commande commande = new Commande(Id_Commande, Date_Commande, HT, TTC, TVA, Id_Client);
            commandeList.add(commande);
        }

        return commandeList;
    }

    public List<Commande> listAllCommandeByIdClient(int idClient) throws SQLException{

        List<Commande> commandeList = new ArrayList<>();

        String queryListAllCommandeContains = "SELECT * FROM Commande WHERE Id_Client= '"+ idClient +"' ; " ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryListAllCommandeContains);

        while (resultSet.next()){
            int Id_Commande = resultSet.getInt("Id_Commande");
            String Date_Commande = resultSet.getString("Date_Commande");
            double HT = resultSet.getDouble("HT");
            double TTC = resultSet.getDouble("TTC");
            double TVA = resultSet.getDouble("TVA");
            int Id_Client =  resultSet.getInt("Id_Client");

            Commande commande = new Commande(Id_Commande, Date_Commande, HT, TTC, TVA, Id_Client);
            commandeList.add(commande);
        }

        return commandeList;
    }

}
