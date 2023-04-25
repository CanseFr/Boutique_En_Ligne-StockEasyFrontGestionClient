package com.app.dao;

import com.app.entity.Commerciaux;
import com.app.entity.Facture;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositoryFacture {

    static Connection connection;

    public RepositoryFacture() throws SQLException {
        connection = DAOConnectionManager.getConnection();
    }

    public ArrayList<Facture> listAllFacture() throws SQLException {
        ArrayList<Facture> factureList = new ArrayList<>();

        String queryAllFacture = "SELECT * FROM Facture";
        Statement Statement = connection.createStatement();
        ResultSet resultSet = Statement.executeQuery(queryAllFacture);

        while(resultSet.next()){
           int Id_Facture = resultSet.getInt("Id_Facture");
           String Date_Facturation = resultSet.getString("Date_Facturation");
           double Total_HT = resultSet.getDouble("Total_HT");
           double Total_TVA = resultSet.getDouble("Total_TVA");
           double Total_TTC = resultSet.getDouble("Total_TTC");
           int Id_Commande = resultSet.getInt("Id_Commande");

            Facture facture = new Facture(Id_Facture,Date_Facturation,Total_HT,Total_TVA,Total_TTC,Id_Commande);
            factureList.add(facture);
        }
        return factureList;
    }


//        "SELECT * FROM Facture WHERE Id_Facture LIKE %"+contain+"% OR Date_Facturation LIKE '%"+contain+"%' OR Total_HT LIKE %"+contain+"% OR Total_TVA LIKE %"+contain+"% OR Total_TTC LIKE %"+contain+"% OR Id_Commande LIKE %"+contain+"%;";
    public List<Facture> listAllFactureContains(String contain) throws SQLException {
        List<Facture> listFacture = new ArrayList<>();

        String queryGetAllFacture = "SELECT Id_Facture,Date_Facturation,Total_HT,Total_TVA,Total_TTC,Id_Commande FROM Facture WHERE Id_Commande LIKE '%"+contain+"%';";

        Statement statement = connection.createStatement();
        ResultSet setRes = statement.executeQuery(queryGetAllFacture);

        while(setRes.next()){
            int Id_Facture = setRes.getInt("Id_Facture");
            String Date_Facturation = setRes.getString("Date_Facturation");
            double Total_HT = setRes.getDouble("Total_HT");
            double Total_TVA = setRes.getDouble("Total_TVA");
            double Total_TTC = setRes.getDouble("Total_TTC");
            int Id_Commande = setRes.getInt("Id_Commande");

            Facture facture = new Facture(Id_Facture, Date_Facturation, Total_HT, Total_TVA ,Total_TTC,Id_Commande);
            listFacture.add(facture);
        }

        return listFacture;
    }

}
