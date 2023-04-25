package com.app.frame;
import com.app.frame.panel.*;
import com.app.frame.panel.Panel4_Commerciaux;

import javax.swing.*;
import java.sql.SQLException;

public class AppPage extends JFrame {
    JTabbedPane tabbedPane = new JTabbedPane();
    public AppPage() throws SQLException {

        super("Gestion Facture Client");

//        // Produits
//        Panel0_Produit p0 = new Panel0_Produit();
//        tabbedPane.addTab("Produit", p0. );

        // Client
        Panel1_Client p1 = new Panel1_Client();
        tabbedPane.addTab("Client", p1.Panel1_Client());

        // Commande
        Panel2_Commande p2 = new Panel2_Commande();
        tabbedPane.addTab("Commande", p2.Panel2_Commande());

        // Facture
        Panel3_Facture p3 = new Panel3_Facture();
        tabbedPane.addTab("Facture", p3.Panel3_Facture());

        // Commerciaux
        Panel4_Commerciaux p4 = new Panel4_Commerciaux();
        tabbedPane.addTab("Commerciaux", p4.Panel4_Commerciaux());

        // Option
        tabbedPane.addTab("Option", Panel5_Option.panel5());

        // Add Pan
        add(tabbedPane);

    }
}





