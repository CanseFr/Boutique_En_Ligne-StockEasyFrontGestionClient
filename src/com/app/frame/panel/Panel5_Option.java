package com.app.frame.panel;

import javax.swing.*;
import java.awt.*;

public class Panel5_Option {

    public static JPanel panel5(){

        JPanel panel5 = new JPanel();

        // Creation Box Align
        Box box = Box.createVerticalBox();
//______________________________________________________________________________________________________________________
//                                                  CREATION FIELD
//______________________________________________________________________________________________________________________

        JLabel labelChangePassWord = new JLabel("Modifier votre mot de passe");

        JLabel userNameLable  = new JLabel("Utilisateur :");
        JTextField userNameField = new JTextField(20);

        JLabel mailLable = new JLabel("Mail :");
        JTextField mailField = new JTextField(40);

        JLabel oldPassLable = new JLabel("Ancien mot de passe");
        JTextField oldPassField = new JPasswordField(20);

        JLabel newPassLable = new JLabel("Nouveau mot de passe");
        JTextField newPassField = new JPasswordField(20);

        JLabel reNewPassLable = new JLabel("Nouveau mot de passe");
        JTextField reNewPassField = new JPasswordField(20);
//______________________________________________________________________________________________________________________
//                                                  ALIGNEMENT
//______________________________________________________________________________________________________________________

        labelChangePassWord.setAlignmentX(Component.LEFT_ALIGNMENT);
        userNameLable.setAlignmentX(Component.LEFT_ALIGNMENT);
        userNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mailLable.setAlignmentX(Component.LEFT_ALIGNMENT);
        mailField.setAlignmentX(Component.LEFT_ALIGNMENT);
        oldPassLable.setAlignmentX(Component.LEFT_ALIGNMENT);
        oldPassField.setAlignmentX(Component.LEFT_ALIGNMENT);
        newPassLable.setAlignmentX(Component.LEFT_ALIGNMENT);
        newPassField.setAlignmentX(Component.LEFT_ALIGNMENT);
        reNewPassLable.setAlignmentX(Component.LEFT_ALIGNMENT);
        reNewPassField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // -> ajout Field -> Box
        box.add(labelChangePassWord);
        box.add(userNameLable);
        box.add(userNameField);
        box.add(mailLable);
        box.add(mailField);
        box.add(oldPassLable);
        box.add(oldPassField);
        box.add(newPassLable);
        box.add(newPassField);
        box.add(reNewPassLable);
        box.add(reNewPassField);

        // -> Ajout box -> panel 5 OTPION
        panel5.add(box);

        return panel5;
    }

}
