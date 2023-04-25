package com.app.frame.panel;

import com.app.dao.RepositoryCommerciaux;
import com.app.dao.RepositoryFacture;
import com.app.entity.Commerciaux;
import com.app.entity.Facture;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Panel3_Facture {
        JPanel panel3 = new JPanel();

        JTextField searchBar;
        JTable jTable;
        List<Facture> factureList; // -> listCommerciaux (correspodance)
    public JPanel Panel3_Facture() throws SQLException {

//______________________________________________________________________________________________________________________
//                                           CREATION TABLEAU
//______________________________________________________________________________________________________________________

        // Element Creation tab
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Date Facturation");
        tableModel.addColumn("Total HT");
        tableModel.addColumn("Total TVA");
        tableModel.addColumn("Total TTC");
        tableModel.addColumn("ID Commande"); // +1 champ attention

        jTable = new JTable(tableModel);

        // Store list
        factureList = new RepositoryFacture().listAllFacture();

        // Store tab
        remplirTableau((ArrayList<Facture>) factureList,tableModel);
//______________________________________________________________________________________________________________________
//                                                  RECHERCHE DANS TABLEAU
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
                    refreshAfterActionButton( tableModel);

                } else {

                    // Vider tableau
                    viderTableaux(tableModel);

                    // Nettoyage liste
                    factureList.clear();

                    // Ajout liste depuis recher
                    try {
                        factureList = new RepositoryFacture().listAllFactureContains(searchBar.getText().toString());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    // Re remplir
                    remplirTableau((ArrayList<Facture>) factureList,tableModel);
                }
            }
        });

//______________________________________________________________________________________________________________________
//                                                 AJOUT PANEL
//______________________________________________________________________________________________________________________

        // Panel de recherche
        JPanel panelSearch = new JPanel(new BorderLayout());
        panelSearch.add(new JLabel("Chercher facture par ID Commande:"), BorderLayout.WEST);
        panelSearch.add(searchBar, BorderLayout.CENTER);

        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(panelSearch,BorderLayout.NORTH);
        panel3.add(new JScrollPane(jTable), BorderLayout.CENTER);

//______________________________________________________________________________________________________________________
//                                                  RETURN DU PANEL
//______________________________________________________________________________________________________________________

        return panel3;
    }
//______________________________________________________________________________________________________________________
//                                          ____________________________
//                                          ____METHODE DE LA CLASSE____
//                                          ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
//
//    Penser a interface pour tous les autres panel
//______________________________________________________________________________________________________________________

    public void remplirTableau(ArrayList<Facture> factureList,DefaultTableModel tableModel){
        for (int i = 0; i < factureList.size() ; i++){
            tableModel.insertRow(0, new Object[] {
                    factureList.get(i).getId_Facture(),
                    factureList.get(i).getDate_Facturation(),
                    factureList.get(i).getTotal_HT(),
                    factureList.get(i).getTotal_TVA(),
                    factureList.get(i).getTotal_TTC(),
                    factureList.get(i).getId_Commande()
            });
        }
    }

    public ArrayList<Facture> getListOfGetAllFactureRepo(){
        try {
            factureList = new RepositoryFacture().listAllFacture();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return (ArrayList<Facture>) factureList;
    }

    public void viderTableaux(DefaultTableModel tableModel){
        for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
            tableModel.removeRow(i);
        }
    }

    public void refreshAfterActionButton(DefaultTableModel tableModel){
        // Vider tableau
        viderTableaux(tableModel);
        // SQL
        factureList = getListOfGetAllFactureRepo();
        // Re remplir
        remplirTableau((ArrayList<Facture>) factureList,tableModel);
    }
}
