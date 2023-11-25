package com.mon;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen {
    JFrame frame;


    public static void main(String[] args) {
        WelcomeScreen ob = new WelcomeScreen();
        ob.CreateScreen();
    }

    public void CreateScreen(){
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setSize(550,309);
        frame.setLayout(null);
        frame.setBounds(450,220,450,309);
        LoadContent();

    }
    public void LoadContent(){
        CustomModelMon ob1 = new CustomModelMon();
        ob1.CreatePanel(0,0,450,309);
        UsedImages obimg = new UsedImages();


        ob1.CreateImage(0,0,550,309,obimg.backj);
        ob1.CreateLabel(100,90,300,100,"Smartie Commercial Bank",ob1.customFont4);
        ob1.label.setForeground(Color.WHITE);

        ob1.CreateProgressBar(0,304,550,5);

        ob1.panel.add(ob1.image_label);
        ob1.image_label.add(ob1.label);
        ob1.image_label.add(ob1.bar);
        frame.add(ob1.panel);
        ImageIcon img = new ImageIcon(obimg.mainIcon);
        frame.setIconImage(img.getImage());
        frame.setVisible(true);
        ob1.fill(frame);



    }


}
