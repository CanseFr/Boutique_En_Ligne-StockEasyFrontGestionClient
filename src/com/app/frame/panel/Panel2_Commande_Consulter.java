package com.app.frame.panel;

import com.app.dao.RepositoryCommande;
import com.app.dao.RepositoryLigneCommande;
import com.app.entity.Commande;
import com.app.entity.LigneCommande;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Panel2_Commande_Consulter extends JFrame {


    JTextField searchBar;
    JTable jTable;
    List<LigneCommande> commandeListProduit;
    int valeurIdSelection ;

    public Panel2_Commande_Consulter(int valeurIdSelection) throws SQLException {

        this.valeurIdSelection = valeurIdSelection;
//______________________________________________________________________________________________________________________
//                                           CREATION TABLEAU
//______________________________________________________________________________________________________________________

        // Element Creation tab
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("ID Ligne Commande");
        defaultTableModel.addColumn("Quantité Commandé");
        defaultTableModel.addColumn("Total HT");
        defaultTableModel.addColumn("Id Produit");
        defaultTableModel.addColumn("Id Commande");


        jTable = new JTable(defaultTableModel);

        // Store list
        commandeListProduit = new RepositoryLigneCommande().listAllLigneCmdByIdCmd(valeurIdSelection);

        // Store tab
        remplirTableau((ArrayList<LigneCommande>) commandeListProduit,defaultTableModel);


//______________________________________________________________________________________________________________________
//                                                  RECHERCHE DANS  :
//        - Recherche commande par ID CLIENT
//______________________________________________________________________________________________________________________

        searchBar = new JTextField(40);
        searchBar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if (searchBar.getText().equals("")){

                    // Vider tableau -> SQL -> Re remplir
                    try {
                        refreshAfterActionButton( defaultTableModel);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                } else {

                    // Vider tableau
                    viderTableaux(defaultTableModel);

                    // Nettoyage liste
                    commandeListProduit.clear();

                    // Ajout liste depuis recher
                    try {
                        System.out.println(valeurIdSelection);
                        commandeListProduit = new RepositoryLigneCommande().listAllLigneCmdByIdCmd(valeurIdSelection);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    // Re remplir
                    remplirTableau((ArrayList<LigneCommande>) commandeListProduit,defaultTableModel);
                }
            }
        });

//______________________________________________________________________________________________________________________
//                                                 AJOUT DES PANELS & RECHERCHE

//        -  Une Commande ne peut etre modifié, seulement par le client via l'interface web
//        - Cliquer sur une commande genere une fenetre avec les ligne de commande
//______________________________________________________________________________________________________________________

        // Sub Panel BOUTTON CRUD
        JPanel subPanel = new JPanel();
        JPanel subTextFieldPanel = new JPanel();//*-*

        // Panel General
        JPanel panel = new JPanel(new BorderLayout());

        // Panel de recherche
        JPanel panelSearch = new JPanel(new BorderLayout());
        panelSearch.add(new JLabel("Chercher commande par ID :"), BorderLayout.WEST);
        panelSearch.add(searchBar, BorderLayout.CENTER);

        panel.add(subPanel,BorderLayout.SOUTH);
        panel.add(subTextFieldPanel, BorderLayout.AFTER_LINE_ENDS); //*-*

        this.setLayout(new BorderLayout());

        add(panelSearch,BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);

        add(new JScrollPane(jTable), BorderLayout.CENTER);


    }
//______________________________________________________________________________________________________________________
//                                          ____________________________
//                                          ____METHODE DE LA CLASSE____
//                                          ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
//______________________________________________________________________________________________________________________

    public void remplirTableau(ArrayList<LigneCommande> commandeListProduit,DefaultTableModel tableModel){
        for (int i = 0; i < commandeListProduit.size() ; i++){
            tableModel.insertRow(0, new Object[] {
                    commandeListProduit.get(i).getId_Commande(),
                    commandeListProduit.get(i).getQuantite_commande(),
                    commandeListProduit.get(i).getPrix_total_Ht(),
                    commandeListProduit.get(i).getId_Commande(),
                    commandeListProduit.get(i).getId_Commande()
            });
        }
    }

    public ArrayList<LigneCommande> getListOfGetAllUserRepo(){
        try {
            commandeListProduit = new RepositoryLigneCommande().listAllLigneCmdByIdCmd(valeurIdSelection);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return (ArrayList<LigneCommande>) commandeListProduit;
    }

    public void viderTableaux(DefaultTableModel tableModel){
        for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
            tableModel.removeRow(i);
        }
    }

    public void refreshAfterActionButton(DefaultTableModel tableModel) throws SQLException {
        // Vider tableau
        viderTableaux(tableModel);
        // SQL
        commandeListProduit = new RepositoryLigneCommande().listAllLigneCmdByIdCmd(valeurIdSelection);
        // Re remplir
        remplirTableau((ArrayList<LigneCommande>) commandeListProduit,tableModel);
    }


}
