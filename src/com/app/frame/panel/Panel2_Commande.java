package com.app.frame.panel;

import com.app.dao.RepositoryClient;
import com.app.dao.RepositoryCommande;
import com.app.dao.RepositoryCommerciaux;
import com.app.entity.Client;
import com.app.entity.Commande;
import com.app.entity.Commerciaux;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Panel2_Commande {
        JPanel panel2 = new JPanel();
        JTable jTable;
        JTextField searchBar;
        List<Commande> commandeList;

    public JPanel Panel2_Commande() throws SQLException {
//______________________________________________________________________________________________________________________
//                                           CREATION TABLEAU
//______________________________________________________________________________________________________________________

        // Element Creation tab
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("ID Commande");
        defaultTableModel.addColumn("Date");
        defaultTableModel.addColumn("HT");
        defaultTableModel.addColumn("TTC");
        defaultTableModel.addColumn("TVA");
        defaultTableModel.addColumn("ID Client");

        jTable = new JTable(defaultTableModel);

        // Store list
        commandeList = new RepositoryCommande().listAllCommande();

        // Store tab
        remplirTableau((ArrayList<Commande>) commandeList,defaultTableModel);


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
                    refreshAfterActionButton( defaultTableModel);

                } else {

                    // Vider tableau
                    viderTableaux(defaultTableModel);

                    // Nettoyage liste
                    commandeList.clear();

                    // Ajout liste depuis recher
                    try {
                        commandeList = new RepositoryCommande().listAllCommandeByIdClientContains(Integer.parseInt(searchBar.getText()));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    // Re remplir
                    remplirTableau((ArrayList<Commande>) commandeList,defaultTableModel);
                }
            }
        });

//______________________________________________________________________________________________________________________
//                                                 CONFIG BOUTTON CRUD
//______________________________________________________________________________________________________________________

        JButton btnConsulterCLient = new JButton("Consulter"); // Consulter

        // Logo BTN
        btnConsulterCLient.setIcon( new ImageIcon("C:\\Users\\julie\\OneDrive\\Documents\\Java\\CDA\\CHALA\\Session PROJET\\TestStockEasy_CLIENT_LOURD\\src\\com\\app\\media\\chercher.png")) ;

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
        subPanel.add( btnConsulterCLient); // Search

        // Panel de recherche
        JPanel panelSearch = new JPanel(new BorderLayout());
        panelSearch.add(new JLabel("Chercher commande par ID Client:"), BorderLayout.WEST);
        panelSearch.add(searchBar, BorderLayout.CENTER);

        panel.add(subPanel,BorderLayout.SOUTH);
        panel.add(subTextFieldPanel, BorderLayout.AFTER_LINE_ENDS); //*-*

        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        panel2.add(panelSearch,BorderLayout.NORTH);
        panel2.add(panel, BorderLayout.SOUTH);

        panel2.add(new JScrollPane(jTable), BorderLayout.CENTER);

//______________________________________________________________________________________________________________________
//                                                ECOUTE DES BOUTTONS
//        Verif -> 0=yes, 1=no, 2=cancel
//______________________________________________________________________________________________________________________

        // CONSULTER
        btnConsulterCLient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valeurIdSelection = (int) jTable.getValueAt(jTable.getSelectedRow(),0);
                System.out.println(valeurIdSelection);
                Panel2_Commande_Consulter panel2_Commande_Consulter = null;
                try {
                    panel2_Commande_Consulter = new Panel2_Commande_Consulter(valeurIdSelection);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                panel2_Commande_Consulter.setSize(800,400);
                panel2_Commande_Consulter.setVisible(true);
                panel2_Commande_Consulter.pack();
                panel2_Commande_Consulter.setLocationRelativeTo(null);
                panel2_Commande_Consulter.setDefaultCloseOperation(panel2_Commande_Consulter.EXIT_ON_CLOSE);
            }
        });


//______________________________________________________________________________________________________________________
//                                                  RETURN DU PANEL
//______________________________________________________________________________________________________________________


        return panel2;
    }
//______________________________________________________________________________________________________________________
//                                          ____________________________
//                                          ____METHODE DE LA CLASSE____
//                                          ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
//
//    Penser a interface pour tous les autres panel
//______________________________________________________________________________________________________________________

    public void remplirTableau(ArrayList<Commande> commandeList,DefaultTableModel tableModel){
        for (int i = 0; i < commandeList.size() ; i++){
            tableModel.insertRow(0, new Object[] {
                    commandeList.get(i).getId_Commande(),
                    commandeList.get(i).getDate_Commande(),
                    commandeList.get(i).getHT(),
                    commandeList.get(i).getTVA(),
                    commandeList.get(i).getTTC(),
                    commandeList.get(i).getId_Client()
            });
        }
    }

    public ArrayList<Commande> getListOfGetAllUserRepo(){
        try {
            commandeList = new RepositoryCommande().listAllCommande();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return (ArrayList<Commande>) commandeList;
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
        commandeList = getListOfGetAllUserRepo();
        // Re remplir
        remplirTableau((ArrayList<Commande>) commandeList,tableModel);
    }

}
