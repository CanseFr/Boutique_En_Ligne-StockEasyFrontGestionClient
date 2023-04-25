package com.app.dao;

import com.app.entity.Commerciaux;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryCommerciaux {

    static Connection connection;

    public RepositoryCommerciaux() throws SQLException{
        connection = DAOConnectionManager.getConnection();
    }

    public void registration(Commerciaux commerciaux) throws SQLException {

        String queryInsertUser = "INSERT INTO Commerciaux (User_Name ,Password ,Mail ,Nom ,Prenom ) VALUES(?,?,?,?,?)";

        PreparedStatement preparedStatementUser = connection.prepareStatement(queryInsertUser);

        preparedStatementUser.setString(1, commerciaux.getUser_Name());
        preparedStatementUser.setString(2, commerciaux.getPassword());
        preparedStatementUser.setString(3, commerciaux.getMail());
        preparedStatementUser.setString(4, commerciaux.getNom());
        preparedStatementUser.setString(5, commerciaux.getPrenom());

        preparedStatementUser.executeUpdate();
    }

    public List<Commerciaux> listAllUser() throws SQLException {

        List<Commerciaux> listCommerciaux = new ArrayList<>();

        String queryGetAllUser = "SELECT Id_User,User_Name ,Mail ,Nom ,Prenom FROM Commerciaux;";
        Statement state = connection.createStatement();
        ResultSet setRes = state.executeQuery(queryGetAllUser);

        while(setRes.next()){
            int Id_User = setRes.getInt("Id_User");
            String User_Name = setRes.getString("User_Name");
            String Mail = setRes.getString("Mail");
            String Nom = setRes.getString("Nom");
            String Prenom = setRes.getString("Prenom");

            Commerciaux commerciaux = new Commerciaux(Id_User, User_Name, Mail, Nom ,Prenom);
            listCommerciaux.add(commerciaux);
        }
        return listCommerciaux;
    }

    public List<Commerciaux> listAllUserContains(String contain) throws SQLException {

        List<Commerciaux> listCommerciaux = new ArrayList<>();

        String queryGetAllUser = "SELECT Id_User,User_Name ,Mail ,Nom ,Prenom FROM Commerciaux WHERE Id_User LIKE'%"+contain+"%' OR User_Name LIKE'%"+contain+"%' OR Mail LIKE'%"+contain+"%' OR Nom LIKE '%"+contain+"%' OR Prenom LIKE '%"+contain+"%';";
        Statement statement = connection.createStatement();
        ResultSet setRes = statement.executeQuery(queryGetAllUser);

        while(setRes.next()){
            int Id_User = setRes.getInt("Id_User");
            String User_Name = setRes.getString("User_Name");
            String Mail = setRes.getString("Mail");
            String Nom = setRes.getString("Nom");
            String Prenom = setRes.getString("Prenom");

            Commerciaux commerciaux = new Commerciaux(Id_User, User_Name, Mail, Nom ,Prenom);
            listCommerciaux.add(commerciaux);
        }

        return listCommerciaux;
    }

    public Commerciaux searchUserByName(String userName) throws SQLException {

        String queryGetUser = "SELECT * FROM Commerciaux WHERE User_Name='"+ userName +"';";
        Statement state = connection.createStatement();
        ResultSet setRes = state.executeQuery(queryGetUser);

        if(!setRes.next()){ // Trouver un try catch et exception propre !
            return new Commerciaux();
        }

        setRes.next();

        int id = setRes.getInt("Id_User");
        String name = setRes.getString("User_Name");
        String mail = setRes.getString("Mail");
        String nom = setRes.getString("Nom");
        String prenom = setRes.getString("Prenom");

        return new Commerciaux(id, name, mail, nom, prenom);

    }

    public Commerciaux searchComById(String idUser) throws SQLException {

        String queryGetUser = "SELECT * FROM Commerciaux WHERE Id_User='"+ idUser +"';";
        Statement state = connection.createStatement();
        ResultSet setRes = state.executeQuery(queryGetUser);

        if(!setRes.next()){ // Trouver un try catch et exception propre !
            return new Commerciaux();
        }

        setRes.next();

        int id = setRes.getInt("Id_User");
        String name = setRes.getString("User_Name");
        String mail = setRes.getString("Mail");
        String nom = setRes.getString("Nom");
        String prenom = setRes.getString("Prenom");

        return new Commerciaux(id, name, mail, nom, prenom);

    }

    public String changePassword(String userName,String mail, String oldPassword, String newPassword, String retypeNewPassword) throws SQLException {

        if (!newPassword.equals(retypeNewPassword)){return "Le mot de passe ressaisie est incorect";}

        String queryGetUser = "SELECT * FROM Commerciaux WHERE User_Name='"+ userName +"' AND Mail ='"+ mail +"' AND Password='"+ oldPassword +"';";
        Statement state = connection.createStatement();
        ResultSet setRes = state.executeQuery(queryGetUser);

        int id_user_DT;

        if(!setRes.next()){
            return "Information d'identification incorrect ! ";
        } else {
            id_user_DT = setRes.getInt("Id_User");
        }

        String queryNewPassUser = "UPDATE Commerciaux SET Commerciaux.Password = ?  WHERE Id_User="+id_user_DT+";";
        PreparedStatement preparedStatementUser = connection.prepareStatement(queryNewPassUser);
        preparedStatementUser.setString(1, newPassword );
        preparedStatementUser.executeUpdate();

        return "Operation validé !";
    }

    public boolean authentificationUser(String userName, String password) throws SQLException {
        String queryAuthentificationUser = "SELECT User_Name,Password FROM Commerciaux WHERE User_Name='"+ userName +"' AND Password='"+ password +"'; ";
        Statement state = connection.createStatement();
        ResultSet setRes = state.executeQuery(queryAuthentificationUser);

        if (!setRes.next()){return false;}

        return true;
    }

    public int getRawUserTable() throws SQLException {
        String queryNbRow = "SELECT count(*) as nbRow FROM Commerciaux ;";
        Statement state = connection.createStatement();
        ResultSet nbRow = state.executeQuery(queryNbRow);

        nbRow.next();
        int countRow = nbRow.getInt("nbRow");

        return countRow;
    }

    public void updateCommercial(Commerciaux commerciaux) throws SQLException {
            String queryyUpdateCommercial = "UPDATE Commerciaux SET  User_Name = '"+commerciaux.getUser_Name()+"', Mail = '"+commerciaux.getMail()+"' ,Nom = '"+commerciaux.getNom()+"', Prenom = '"+commerciaux.getPrenom()+"'      WHERE Id_User="+commerciaux.getId_User()+" ;";
            Statement state = connection.createStatement();
            state.executeUpdate(queryyUpdateCommercial);
    }

    public static void deleteCommercial(int idCommercial) throws SQLException {
        String queryDeleteCommercial = "DELETE FROM Commerciaux WHERE Id_User="+idCommercial+"";
        Statement state = connection.createStatement();
        state.executeUpdate(queryDeleteCommercial);
    }

}
