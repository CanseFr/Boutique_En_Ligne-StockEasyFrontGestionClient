import com.app.frame.HomePage;

import javax.swing.*;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {

        // HOME FRAME
        HomePage homePage=new HomePage();
        homePage.setSize(800,800);
        homePage.setVisible(true);
        homePage.pack();
        homePage.setLocationRelativeTo(null);
        homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
    }
