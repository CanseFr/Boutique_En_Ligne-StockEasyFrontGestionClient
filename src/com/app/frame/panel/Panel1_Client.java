package com.app.frame.panel;

import com.app.dao.RepositoryClient;
import com.app.dao.RepositoryCommerciaux;
import com.app.entity.Client;
import com.app.entity.Commerciaux;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Panel1_Client {
    JPanel panel1;
    JTextField searchBar;
    JTable jTable;
    List<Client> clientList;

    public JPanel Panel1_Client() throws SQLException {
//______________________________________________________________________________________________________________________
//                                           CREATION TABLEAU
//______________________________________________________________________________________________________________________

        // Element creation tab
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID Client");
        tableModel.addColumn("User Name");
        tableModel.addColumn("Mail");
        tableModel.addColumn("Adresse");
        tableModel.addColumn("CP");
        tableModel.addColumn("Ville");

        jTable = new JTable(tableModel);

        // Store list
        clientList = new RepositoryClient().listAllClient();

        // Store tab
        remplirTableau((ArrayList<Client>) clientList,tableModel);


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
                    clientList.clear();

                    // Ajout liste depuis recher
                    try {
                        clientList = new RepositoryClient().listAllClientContains(searchBar.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    // Re remplir
                    remplirTableau((ArrayList<Client>) clientList,tableModel);
                }
            }
        });
//______________________________________________________________________________________________________________________
//                                                 CONFIG BOUTTON CRUD
//______________________________________________________________________________________________________________________

        // Sub Panel BOUTTON CRUD
        JPanel subPanel = new JPanel();
        JPanel subTextFieldPanel = new JPanel();//*-*

        // Btn CRUD
        JButton btnAjout = new JButton("Ajouter");//
        JButton btnModif = new JButton("Modifier");//
        JButton btnDelete = new JButton("Supprimer");//
        JButton btnConsulterCLient = new JButton("Consulter"); // Consulter

        // TXT FIELD CRUD
        JTextField txtFieldIdClient = new JTextField(3); // *-*
        JTextField txtFieldUserName = new JTextField(20); // *-*
        JTextField txtFieldMail = new JTextField(20); // *-*
        JTextField txtFieldAdresse = new JTextField(20); // *-*
        JTextField txtFieldCp = new JTextField(5); // *-*
        JTextField txtFieldVille = new JTextField(10); // *-*


        // LBL CRUD
        JLabel txtLabelIdClient = new JLabel("ID : "); // ---
        JLabel txtLabelUserName = new JLabel("User Name : "); // ---
        JLabel txtLabelMail = new JLabel("Mail : "); // ---
        JLabel txtLabelAdresse = new JLabel("Adresse : "); // ---
        JLabel txtLabelCp = new JLabel("CP : "); // ---
        JLabel txtLabelVille = new JLabel("Ville : "); // ---

        // Logo BTN
        btnAjout.setIcon(new ImageIcon("C:\\Users\\julie\\OneDrive\\Documents\\Java\\CDA\\CHALA\\Session PROJET\\TestStockEasy_CLIENT_LOURD\\src\\com\\app\\media\\plus.png"));
        btnModif.setIcon(new ImageIcon("C:\\Users\\julie\\OneDrive\\Documents\\Java\\CDA\\CHALA\\Session PROJET\\TestStockEasy_CLIENT_LOURD\\src\\com\\app\\media\\file.png")) ;
        btnDelete.setIcon( new ImageIcon("C:\\Users\\julie\\OneDrive\\Documents\\Java\\CDA\\CHALA\\Session PROJET\\TestStockEasy_CLIENT_LOURD\\src\\com\\app\\media\\delete.png")) ;
        btnConsulterCLient.setIcon( new ImageIcon("C:\\Users\\julie\\OneDrive\\Documents\\Java\\CDA\\CHALA\\Session PROJET\\TestStockEasy_CLIENT_LOURD\\src\\com\\app\\media\\chercher.png")) ;

//______________________________________________________________________________________________________________________
//                                                 AJOUT BOUTTON
//______________________________________________________________________________________________________________________

        subPanel.add(btnAjout);
        subPanel.add( btnModif);
        subPanel.add( btnDelete);
        subPanel.add( btnConsulterCLient); // Search

        // Set lbl & field
        subTextFieldPanel.add(txtLabelIdClient);//*-*
        subTextFieldPanel.add(txtFieldIdClient); // ---

        subTextFieldPanel.add(txtLabelUserName);//*-*
        subTextFieldPanel.add(txtFieldUserName); // ---

        subTextFieldPanel.add(txtLabelMail);//*-*
        subTextFieldPanel.add(txtFieldMail); // ---

        subTextFieldPanel.add(txtLabelAdresse);//*-*
        subTextFieldPanel.add(txtFieldAdresse); // ---

        subTextFieldPanel.add(txtLabelCp);//*-*
        subTextFieldPanel.add(txtFieldCp); // ---

        subTextFieldPanel.add(txtLabelVille);//*-*
        subTextFieldPanel.add(txtFieldVille); // ---

        // Panel General
        JPanel panel = new JPanel(new BorderLayout());

        // Panel de recherche
        JPanel panelSearch = new JPanel(new BorderLayout());
        panelSearch.add(new JLabel("Chercher un client:"), BorderLayout.WEST);
        panelSearch.add(searchBar, BorderLayout.CENTER);

        panel.add(subPanel,BorderLayout.SOUTH);
        panel.add(subTextFieldPanel, BorderLayout.AFTER_LINE_ENDS); //*-*

        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        panel1.add(panelSearch,BorderLayout.NORTH);
        panel1.add(panel, BorderLayout.SOUTH);

        panel1.add(new JScrollPane(jTable), BorderLayout.CENTER);

//______________________________________________________________________________________________________________________
//                                                  ECOUTE DES LIGNES
//______________________________________________________________________________________________________________________

        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtFieldIdClient.setText(jTable.getValueAt(jTable.getSelectedRow(), 0  ).toString());
                txtFieldUserName.setText(jTable.getValueAt(jTable.getSelectedRow(), 1  ).toString());
                txtFieldMail.setText(jTable.getValueAt(jTable.getSelectedRow(), 2  ).toString());
                txtFieldAdresse.setText(jTable.getValueAt(jTable.getSelectedRow(), 3  ).toString());
                txtFieldCp.setText(jTable.getValueAt(jTable.getSelectedRow(), 4  ).toString());
                txtFieldVille.setText(jTable.getValueAt(jTable.getSelectedRow(), 5  ).toString());
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        jTable.getSelectedRow();


        // Nettoyage des champs crud pour eviter bug
        searchBar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {

            txtFieldIdClient.setText("");
            txtFieldUserName.setText("");
            txtFieldMail.setText("");
            txtFieldAdresse.setText("");
            txtFieldCp.setText("");
            txtFieldVille.setText("");

            }
        });
//______________________________________________________________________________________________________________________
//                                                ECOUTE DES BOUTTONS
//        Verif -> 0=yes, 1=no, 2=cancel
//______________________________________________________________________________________________________________________

        // AJOUT -> Creer une fenetre ?
        btnAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Client newClient;

                if (    txtFieldIdClient.getText().equals("") ||
                        txtFieldUserName.getText().equals("") ||
                        txtFieldMail.getText().equals("")     ||
                        txtFieldAdresse.getText().equals("")  ||
                        txtFieldCp.getText().equals("")       ||
                        txtFieldVille.getText().equals("")      ){

                    showMessageDialog(null, "Veuillez remplir tous les champs !");
                }
                else {
                    // Recuperation des champs -> Objet
                    newClient = new Client(
                            Integer.parseInt(txtFieldIdClient.getText().trim()),
                            txtFieldUserName.getText().trim(),
                            txtFieldMail.getText().trim(),
                            txtFieldAdresse.getText().trim(),
                            txtFieldCp.getText().trim(),
                            txtFieldVille.getText().trim()
                    );

                    // VERIF
                    int choixAddClient = JOptionPane.showConfirmDialog(null, "Voulez vous Ajouter "+newClient.getUser_Name()+" ?", "Votre choix ...",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

                    // SQL Update
                    if (choixAddClient == 0){
                        try {
                            // Update
                            new RepositoryClient().registration(newClient);
                            showMessageDialog(null, "Client "+newClient.getUser_Name()+" Ajouté !");
                            // Refresh apres clique
                            refreshAfterActionButton( tableModel);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        // ne fais rien
                    }
                }
            }
        });

        // MODIF
        btnModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtFieldIdClient.getText().equals("")){

                    showMessageDialog(null, "L'id est nécessaire pour identifier le client à modifier !");

                } else if (  txtFieldIdClient.getText().equals("") ||
                             txtFieldUserName.getText().equals("") ||
                             txtFieldMail.getText().equals("")     ||
                             txtFieldAdresse.getText().equals("")  ||
                             txtFieldCp.getText().equals("")       ||
                             txtFieldVille.getText().equals("") ) {


                    showMessageDialog(null, "Attention tous les champs doivent etre present !");

                } else {
                    // Recuperation des champs -> Objet
                    Client newClient = new Client(

                            Integer.parseInt(txtFieldIdClient.getText().trim()),
                            txtFieldUserName.getText().trim(),
                            txtFieldMail.getText().trim(),
                            txtFieldAdresse.getText().trim(),
                            txtFieldCp.getText().trim(),
                            txtFieldVille.getText().trim()
                    );

                    // VERIF
                    int choixAddUser = JOptionPane.showConfirmDialog(null, "Voulez vous Mettre a jour  "+newClient.getUser_Name()+" ?", "Votre choix ...",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

                    // SQL Update
                    if (choixAddUser == 0){
                        try {
                            // Update
                            new RepositoryClient().updateClient(newClient);
                            showMessageDialog(null, "Mise a jour validé !");

                            // Refresh apres clique
                            refreshAfterActionButton( tableModel);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        // ne fais rien
                    }
                }
            }
        });

        // DELETE
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtFieldIdClient.getText().equals("")){

                    showMessageDialog(null, "L'id est nécessaire pour supprimer un client, veuillez en selectioner un !");

                } else {

                    // VERIF
                    int choixDeleteUser = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer "+txtFieldUserName.getText().toString()+"", "Votre choix ...",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

                    // Confirmation fenetre
                    if (choixDeleteUser == 0){
                        String idCltSupprimerStr = txtFieldIdClient.getText().toString().trim();

                        // Try str to int
                        try {
                            int idCltSupprimerInt = Integer.parseInt(idCltSupprimerStr);
                            if (idCltSupprimerInt < 1 || idCltSupprimerInt > 999){
                                System.out.println("Id Innexistant");
                            } else {
                                // Executer requete delete
                                RepositoryClient.deleteClient(idCltSupprimerInt);
                            }

                        }catch (Exception er){
                            er.getMessage();
                            System.out.println("Erreur Id");
                            //JOptionPane.showMessageDialog();
                        }

                        // Refresh apres clique
                        refreshAfterActionButton( tableModel);
                    } else {
                        // Rien retour panel
                    }
                }
            }
        });

        // CONSULTER
        btnConsulterCLient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valeurIdSelection = (int) jTable.getValueAt(jTable.getSelectedRow(),0);
                System.out.println(valeurIdSelection);
                Panel1_Client_Consulter panel1ClientConsulter = null;
                try {
                    panel1ClientConsulter = new Panel1_Client_Consulter(valeurIdSelection);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                panel1ClientConsulter.setSize(800,400);
                panel1ClientConsulter.setVisible(true);
                panel1ClientConsulter.pack();
                panel1ClientConsulter.setLocationRelativeTo(null);
                panel1ClientConsulter.setDefaultCloseOperation(panel1ClientConsulter.EXIT_ON_CLOSE);
            }
        });

//______________________________________________________________________________________________________________________
//                                                  RETURN DU PANEL
//______________________________________________________________________________________________________________________

        return panel1;
    }

//______________________________________________________________________________________________________________________
//                                          ____________________________
//                                          ____METHODE DE LA CLASSE____
//                                          ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
//
//    Penser a interface pour tous les autres panel
//______________________________________________________________________________________________________________________

    public void remplirTableau(ArrayList<Client> clientList, DefaultTableModel tableModel){
        for (int i = 0; i < clientList.size() ; i++){
            tableModel.insertRow(0, new Object[] {
                    clientList.get(i).getId_Client(),
                    clientList.get(i).getUser_Name(),
                    clientList.get(i).getMail(),
                    clientList.get(i).getAdresse(),
                    clientList.get(i).getCP(),
                    clientList.get(i).getVille(),
            });

        }
    }

    public ArrayList<Client> getListOfGetAllClientRepo(){
        try {
            clientList = new RepositoryClient().listAllClient();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return (ArrayList<Client>) clientList;
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
        clientList = getListOfGetAllClientRepo();
        // Re remplir
        remplirTableau((ArrayList<Client>) clientList,tableModel);
    }

}
