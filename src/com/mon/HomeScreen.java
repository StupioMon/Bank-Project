package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeScreen {
    JFrame frame;

    public static void main(String[] args) {
        HomeScreen ob = new HomeScreen();
        ob.CreateScreen();
    }
    public void CreateScreen(){
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setBounds(350,200,700,400);
        loadContent();
        frame.setVisible(true);
    }
    public void loadContent(){
        CustomModelMon ob1 = new CustomModelMon();
        CustomModelMon ob2 = new CustomModelMon();
        CustomModelMon ob3 = new CustomModelMon();

        ob1.CreateTitlePanel(0,0,660,400,frame);
        ob1.titlePanel.setBackground(ob1.darkBack);

        JPanel textPanel = new JPanel();
        textPanel.setBounds(0,70,700,50);
        textPanel.setBackground(ob1.darkBack);

        JLabel bankName = new JLabel("Smartie Commercial Bank Ltd.",SwingConstants.CENTER);
        bankName.setFont(ob1.customFont3);
        bankName.setForeground(Color.WHITE);


        textPanel.add(bankName);

        UsedImages obimg = new UsedImages();

        ob1.CreateCustomButton(100,140,150,140,"Admin",obimg.RoyalAdmin);
        ob2.CreateCustomButton(270,140,150,140,"User",obimg.userss);
        ob3.CreateCustomButton(430,140,150,140,"Register",obimg.regss);


        ob1.CreateCrossButton(650,0,50,30,frame);

        ob2.customButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginUser ob = new LoginUser();
                frame.setVisible(false);
                ob.loadUserLogin();
            }
        });

        ob1.customButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginAdmin ob = new LoginAdmin();
                frame.setVisible(false);
                ob.loadAdmin();

            }
        });


        ob3.customButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Register loadReg = new Register();
                frame.setVisible(false);
                loadReg.loadRegister();

            }
        });

        ob1.crossPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ob1.titlePanel.add(ob1.crossPanel);
        ob1.titlePanel.add(textPanel);
        ob1.titlePanel.add(ob1.customButton);
        ob1.titlePanel.add(ob2.customButton);
        ob1.titlePanel.add(ob3.customButton);

        UsedImages obimgs = new UsedImages();
        ImageIcon img = new ImageIcon(obimgs.mainIcon);
        frame.setIconImage(img.getImage());
        frame.add(ob1.titlePanel);


    }
}
