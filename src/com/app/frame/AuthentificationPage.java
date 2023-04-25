package com.app.frame;

import com.app.dao.RepositoryCommerciaux;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static javax.swing.JOptionPane.showMessageDialog;

public class AuthentificationPage extends JFrame implements ActionListener {
//______________________________________________________________________________________________________________________
//                                              ELEMENT DU PANEL
//______________________________________________________________________________________________________________________
    // Username
    JLabel labelUserName ;
    JTextField fieldUserName;

    // UserPassword
    JLabel labelPassword ;
    JTextField fieldPassword;

    // Send Button
    JButton b;

    // Le panel
    JPanel p ;

//______________________________________________________________________________________________________________________
//                                              AUTHENTIFICATION
//______________________________________________________________________________________________________________________
    public AuthentificationPage(){

        // Elements panel
        // User Name
        labelUserName = new JLabel("Utilisateur");
        fieldUserName = new JTextField(20);
        // User Password
        labelPassword = new JLabel("Mot de passe");
        fieldPassword = new JPasswordField(20);
        // Tech
        b = new JButton("Valider");
        p = new JPanel();

        // Listen
        b.addActionListener(this);

        // Panel, highlight boutton par defaut
        getRootPane().setDefaultButton(b);

        // Ajout element au panel
        p.add(labelUserName);
        p.add(fieldUserName);
        p.add(labelPassword);
        p.add(fieldPassword);
        p.add(b);


        Border br = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.orange),"Login", TitledBorder.CENTER, TitledBorder.LEFT);
        p.setBorder(br);
        setLayout(new FlowLayout());

        // Ajout du panel a JFrame
        add(p);

    }
//______________________________________________________________________________________________________________________
//                                              EVENT
//______________________________________________________________________________________________________________________
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Valider")){

            String user_name = fieldUserName.getText();
            String password = fieldPassword.getText();
            boolean auth;

            try {
                auth = new RepositoryCommerciaux().authentificationUser(user_name,password);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (!auth){
                showMessageDialog(null, "Mot de passe incorrect !");
            }else {

                System.out.println("Connection OK !");
                this.setVisible(false);

                // Lancement App
                AppPage appPage= null;

                try {
                    appPage = new AppPage();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                appPage.setSize(800,800);
                appPage.setVisible(true);
                appPage.pack();
                appPage.setLocationRelativeTo(null);
                appPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        }
    }
}
