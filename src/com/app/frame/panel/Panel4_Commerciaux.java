package com.app.frame.panel;

import com.app.dao.RepositoryCommerciaux;
import com.app.entity.Commerciaux;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Panel4_Commerciaux {
    JPanel panel4;
    JTextField searchBar;
    JTable jTable;
    List<Commerciaux> listCommerciaux;
    public JPanel Panel4_Commerciaux() throws SQLException {
//______________________________________________________________________________________________________________________
//                                           CREATION TABLEAU
//______________________________________________________________________________________________________________________

        // Element Creation tab
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("UserName");
        tableModel.addColumn("Mail");
        tableModel.addColumn("Nom");
        tableModel.addColumn("Prenom");
        jTable = new JTable(tableModel);

        // Store list
        listCommerciaux = new RepositoryCommerciaux().listAllUser();

        // Store tab
        remplirTableau((ArrayList<Commerciaux>) listCommerciaux,tableModel);

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
                    listCommerciaux.clear();

                    // Ajout liste depuis recher
                    try {
                        listCommerciaux = new RepositoryCommerciaux().listAllUserContains(searchBar.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    // Re remplir
                    remplirTableau((ArrayList<Commerciaux>) listCommerciaux,tableModel);
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

        // TXT FIELD CRUD
        JTextField txtFieldIdUser = new JTextField(3); // *-*
        JTextField txtFieldUserName = new JTextField(20); // *-*
        JTextField txtFieldMail = new JTextField(20); // *-*
        JTextField txtFieldNom = new JTextField(20); // *-*
        JTextField txtFieldPrenom = new JTextField(20); // *-*

        // LBL CRUD
        JLabel txtLabelIdUser = new JLabel("Id : "); // ---
        JLabel txtLabelUserName = new JLabel("User Name : "); // ---
        JLabel txtLabelMail = new JLabel("Mail : "); // ---
        JLabel txtLabelNom = new JLabel("Nom : "); // ---
        JLabel txtLabelPrenom = new JLabel("Prenom : "); // ---

        // Logo BTN
        btnAjout.setIcon(new ImageIcon("C:\\Users\\julie\\OneDrive\\Documents\\Java\\CDA\\CHALA\\Session PROJET\\TestStockEasy_CLIENT_LOURD\\src\\com\\app\\media\\plus.png"));
        btnModif.setIcon(new ImageIcon("C:\\Users\\julie\\OneDrive\\Documents\\Java\\CDA\\CHALA\\Session PROJET\\TestStockEasy_CLIENT_LOURD\\src\\com\\app\\media\\file.png")) ;
        btnDelete.setIcon( new ImageIcon("C:\\Users\\julie\\OneDrive\\Documents\\Java\\CDA\\CHALA\\Session PROJET\\TestStockEasy_CLIENT_LOURD\\src\\com\\app\\media\\delete.png")) ;

//______________________________________________________________________________________________________________________
//                                                 AJOUT BOUTTON
//______________________________________________________________________________________________________________________

        subPanel.add(btnAjout);
        subPanel.add( btnModif);
        subPanel.add( btnDelete);

        // Set lbl & field
        subTextFieldPanel.add(txtLabelIdUser); // ---
        subTextFieldPanel.add(txtFieldIdUser);//*-*

        subTextFieldPanel.add(txtLabelUserName); // ---
        subTextFieldPanel.add(txtFieldUserName);//*-*

        subTextFieldPanel.add(txtLabelMail); // ---
        subTextFieldPanel.add(txtFieldMail);//*-*

        subTextFieldPanel.add(txtLabelNom); // ---
        subTextFieldPanel.add(txtFieldNom);//*-*

        subTextFieldPanel.add(txtLabelPrenom); // ---
        subTextFieldPanel.add(txtFieldPrenom);//*-*

        // Panel General
        JPanel panel = new JPanel(new BorderLayout());

        // Panel de recherche
        JPanel panelSearch = new JPanel(new BorderLayout());
        panelSearch.add(new JLabel("Chercher un commercial:"), BorderLayout.WEST);
        panelSearch.add(searchBar, BorderLayout.CENTER);

        panel.add(subPanel,BorderLayout.SOUTH);
        panel.add(subTextFieldPanel, BorderLayout.AFTER_LINE_ENDS); //*-*

        panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());

        panel4.add(panelSearch,BorderLayout.NORTH);
        panel4.add(panel, BorderLayout.SOUTH);

        panel4.add(new JScrollPane(jTable), BorderLayout.CENTER);
//______________________________________________________________________________________________________________________
//                                                  ECOUTE DES LIGNES
//______________________________________________________________________________________________________________________

        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtFieldIdUser.setText(jTable.getValueAt(jTable.getSelectedRow(),0).toString());
                txtFieldUserName.setText(jTable.getValueAt(jTable.getSelectedRow(),1).toString());
                txtFieldMail.setText(jTable.getValueAt(jTable.getSelectedRow(),2).toString());
                txtFieldNom.setText(jTable.getValueAt(jTable.getSelectedRow(),3).toString());
                txtFieldPrenom.setText(jTable.getValueAt(jTable.getSelectedRow(),4).toString());
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

                txtFieldIdUser.setText("");
                txtFieldUserName.setText("");
                txtFieldMail.setText("");
                txtFieldNom.setText("");
                txtFieldPrenom.setText("");

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

                Commerciaux newCommercial;

                if (txtFieldUserName.getText().equals("") ||
                    txtFieldMail.getText().equals("")     ||
                    txtFieldNom.getText().equals("")      ||
                    txtFieldPrenom.getText().equals("") ){

                    showMessageDialog(null, "Veuillez remplir tous les champs !");
                }
                else {
                    // Recuperation des champs -> Objet
                    newCommercial = new Commerciaux(
                            txtFieldUserName.getText().trim(),
                            "InitialPassWord",
                            txtFieldMail.getText().trim(),
                            txtFieldNom.getText().trim(),
                            txtFieldPrenom.getText().trim()
                    );

                    // VERIF
                    int choixAddUser = JOptionPane.showConfirmDialog(null, "Voulez vous Ajouter "+newCommercial.getUser_Name()+" ?", "Votre choix ...",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

                    // SQL Update
                    if (choixAddUser == 0){
                        try {
                            // Update
                            new RepositoryCommerciaux().registration(newCommercial);
                            showMessageDialog(null, "Commercial Ajouté !");
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
                if (txtFieldIdUser.getText().equals("")){

                    showMessageDialog(null, "L'id est nécessaire pour identifier le commercial à modifier !");

                } else if ( txtFieldUserName.getText().equals("") ||
                            txtFieldMail.getText().equals("")     ||
                            txtFieldNom.getText().equals("")      ||
                            txtFieldPrenom.getText().equals("") ) {

                    showMessageDialog(null, "Attention tous les champs doivent etre present !");

                } else {
                    // Recuperation des champs -> Objet
                    Commerciaux newCommercial = new Commerciaux(
                            Integer.parseInt(txtFieldIdUser.getText().trim()),
                            txtFieldUserName.getText().trim(),
                            txtFieldMail.getText().trim(),
                            txtFieldNom.getText().trim(),
                            txtFieldPrenom.getText().trim()
                    );

                    // VERIF
                    int choixAddUser = JOptionPane.showConfirmDialog(null, "Voulez vous Mettre a jour  "+newCommercial.getUser_Name()+" ?", "Votre choix ...",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

                    // SQL Update
                    if (choixAddUser == 0){
                        try {
                            // Update
                            new RepositoryCommerciaux().updateCommercial(newCommercial);
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
                if (txtFieldIdUser.getText().equals("")){

                    showMessageDialog(null, "L'id est nécessaire pour supprimer un commercial, veuillez en selectioner un !");

                } else {

                    // VERIF
                    int choixDeleteUser = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer "+txtFieldUserName.getText().toString()+"", "Votre choix ...",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

                    // Confirmation fenetre
                    if (choixDeleteUser == 0){
                        String idComSupprimerStr = txtFieldIdUser.getText().toString().trim();

                        // Try str to int
                        try {
                            int idComSupprimerInt = Integer.parseInt(idComSupprimerStr);
                            if (idComSupprimerInt < 1 || idComSupprimerInt > 999){
                                System.out.println("Id Innexistant");
                            } else {
                                // Executer requete delete
                                RepositoryCommerciaux.deleteCommercial(idComSupprimerInt);
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

//______________________________________________________________________________________________________________________
//                                                  RETURN DU PANEL
//______________________________________________________________________________________________________________________

        return panel4;
    }

//______________________________________________________________________________________________________________________
//                                          ____________________________
//                                          ____METHODE DE LA CLASSE____
//                                          ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
//
//    Penser a interface pour tous les autres panel
//______________________________________________________________________________________________________________________

    public void remplirTableau(ArrayList<Commerciaux> listCommerciaux,DefaultTableModel tableModel){
        for (int i = 0; i < listCommerciaux.size() ; i++){
            tableModel.insertRow(0, new Object[] {
                    listCommerciaux.get(i).getId_User(),
                    listCommerciaux.get(i).getUser_Name(),
                    listCommerciaux.get(i).getMail(),
                    listCommerciaux.get(i).getNom(),
                    listCommerciaux.get(i).getPrenom()
            });
        }
    }

    public ArrayList<Commerciaux> getListOfGetAllUserRepo(){
        try {
            listCommerciaux = new RepositoryCommerciaux().listAllUser();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return (ArrayList<Commerciaux>) listCommerciaux;
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
        listCommerciaux = getListOfGetAllUserRepo();
        // Re remplir
        remplirTableau((ArrayList<Commerciaux>) listCommerciaux,tableModel);
    }

}

