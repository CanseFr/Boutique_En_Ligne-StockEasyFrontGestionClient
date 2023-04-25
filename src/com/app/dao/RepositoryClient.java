package com.app.dao;

import com.app.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryClient {

    static Connection connection;

    public RepositoryClient() throws SQLException {
        connection = DAOConnectionManager.getConnection();
    }

    public List<Client> listAllClient() throws SQLException{
        List<Client> clientList = new ArrayList<>();

        String queryListAllCLient = "SELECT * FROM Client";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryListAllCLient);

        while (resultSet.next()){

            int Id_Client = resultSet.getInt("Id_Client");
            String User_Name = resultSet.getString("User_Name");
            String Mail = resultSet.getString("Mail");
            String Adresse = resultSet.getString("Adresse");
            String CP = resultSet.getString("CP");
            String Ville = resultSet.getString("Ville");

            Client client = new Client(Id_Client, User_Name, Mail, Adresse, CP, Ville);
            clientList.add(client);
        }
        return clientList;
    }

    public List<Client> listAllClientContains(String contains) throws SQLException{
        List<Client> clientListContains = new ArrayList<>();

        String queryAllClientContains = "SELECT * FROM client WHERE Id_Client LIKE '%"+contains+"%' OR User_Name LIKE '%"+contains+"%' OR Mail LIKE '%"+contains+"%' OR Adresse LIKE '%"+contains+"%' OR CP LIKE '%"+contains+"%' OR Ville LIKE '%"+contains+"%';";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryAllClientContains);

        while (resultSet.next()){

            int Id_Client = resultSet.getInt("Id_Client");
            String User_Name = resultSet.getString("User_Name");
            String Mail  = resultSet.getString("Mail");
            String Adresse  = resultSet.getString("Adresse");
            String CP  = resultSet.getString("CP");
            String Ville  = resultSet.getString("Ville");

            Client client = new Client(Id_Client, User_Name, Mail, Adresse, CP, Ville);
            clientListContains.add(client);
        }

        return clientListContains;
    }

    public void registration(Client client) throws SQLException {

        String queryRegisterClient = "INSERT INTO Client (User_Name,Password,Mail,Adresse,CP,Ville) VALUES (?,?,?,?,?,?)  ";

        PreparedStatement preparedStatement = connection.prepareStatement(queryRegisterClient);

        preparedStatement.setString(1,client.getUser_Name());
        preparedStatement.setString(2,"INITPASSWORD");
        preparedStatement.setString(3,client.getMail());
        preparedStatement.setString(4,client.getAdresse());
        preparedStatement.setString(5,client.getCP());
        preparedStatement.setString(6,client.getVille());

        preparedStatement.executeUpdate();

    }

    public void updateClient(Client client) throws SQLException{
        String queryyUpdateClient = "UPDATE Client SET  User_Name = '"+client.getUser_Name()+"', Mail = '"+client.getMail()+"' ,Adresse = '"+client.getAdresse()+"', CP = '"+client.getCP()+"' , Ville =  '"+client.getVille()+"'     WHERE Id_Client="+client.getId_Client()+" ;";
        Statement state = connection.createStatement();
        state.executeUpdate(queryyUpdateClient);
    }

    public static void deleteClient(int idClient) throws SQLException{

        String queryDeleteClient = "DELETE FROM Client WHERE Id_Client ="+idClient+"";
        Statement state = connection.createStatement();
        state.executeUpdate(queryDeleteClient);

    }

}
