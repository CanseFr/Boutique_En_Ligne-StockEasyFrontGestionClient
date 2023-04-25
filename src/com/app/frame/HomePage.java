package com.app.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePage extends JFrame implements ActionListener {


    JLabel l ;
    JButton b;
    JButton d;
    int count = 0;

    public HomePage(){
        super("Welcome");

        setLayout(new FlowLayout());

        // Creation
        l=new JLabel();
        b=new JButton("S'identifier");
        d=new JButton("Fermer");

        //Ajout
        add(l);
        add(b);
        add(d);


        // Listener
        b.addActionListener(this);
        d.addActionListener(this);

        // Panel, highlight boutton par defaut
        getRootPane().setDefaultButton(b);

        // Icon
        l.setIcon(new ImageIcon("C:\\Users\\julie\\OneDrive\\Documents\\Java\\CDA\\CHALA\\Session PROJET\\TestStockEasy_CLIENT_LOURD\\src\\com\\app\\media\\facture-dachat.png"));
        b.setIcon(new ImageIcon("C:\\Users\\julie\\OneDrive\\Documents\\Java\\CDA\\CHALA\\Session PROJET\\TestStockEasy_CLIENT_LOURD\\src\\com\\app\\media\\check.png"));
        d.setIcon(new ImageIcon("C:\\Users\\julie\\OneDrive\\Documents\\Java\\CDA\\CHALA\\Session PROJET\\TestStockEasy_CLIENT_LOURD\\src\\com\\app\\media\\remove.png"));

        // Alt cursor, information passage de la souris
        b.setToolTipText("S'identifier");
        d.setToolTipText("Fermer");


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("S'identifier")){

            this.setVisible(false);

            AuthentificationPage authentificationPage=new AuthentificationPage();
            authentificationPage.setSize(800,150);
            authentificationPage.setVisible(true);
            authentificationPage.pack();
            authentificationPage.setLocationRelativeTo(null);
            authentificationPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } else if (e.getActionCommand().equals("Fermer")){
            System.exit(0);
        }
    }
}
