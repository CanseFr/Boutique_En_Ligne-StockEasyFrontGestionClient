package com.app.dao;

import com.app.entity.Commande;
import com.app.entity.LigneCommande;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepositoryLigneCommande {
    static Connection connection;
    public RepositoryLigneCommande() throws SQLException {
        connection = DAOConnectionManager.getConnection();
    }

    public List<LigneCommande> listAllLigneCmdByIdCmd(int idCmd) throws SQLException{

        List<LigneCommande> ligneCommandeList = new ArrayList<>();

        String queryListAllligneCommandeListContains = "SELECT * FROM Ligne_Commande WHERE Id_Commande = '"+ idCmd +"' ; " ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryListAllligneCommandeListContains);

        while (resultSet.next()){
            int Id_LigneCommande = resultSet.getInt("Id_Ligne_Commande");
            int quantiteCmd = resultSet.getInt("Quantite_commande");
            double HT = resultSet.getDouble("Prix_total_Ht");
            int idProduit = resultSet.getInt("Id_Produit");
            int idCommande = resultSet.getInt("Id_Commande");



            LigneCommande ligne = new LigneCommande(Id_LigneCommande, quantiteCmd, HT, idProduit, idCommande);
            ligneCommandeList.add(ligne);
        }

        return ligneCommandeList;
    }

}




