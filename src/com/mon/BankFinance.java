package com.mon;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankFinance {



        public static void main(String[] args) {
            BankFinance ob = new BankFinance();
            ob.loadFinance();

        }
        public void loadFinance(){

            CreateWindow ob1 = new CreateWindow(250,50,900,580);


            CustomModelMon ob2 = new CustomModelMon();
            CustomModelMon ob3 = new CustomModelMon();
            CustomModelMon ob4 = new CustomModelMon();


            ob2.CreateLabel(100,60,300,50,"Bank Financial Overview",ob2.customFont4);
            ob2.label.setForeground(Color.WHITE);

            UsedImages obimg = new UsedImages();

            ob2.CreateImage(400,200,100,100,obimg.money_bag);

            JPanel newPanel = new JPanel();
            newPanel.setBounds(0,330,900,100);
            newPanel.setBackground(ob2.darkBack);

            CustomModelMon obx = new CustomModelMon();
            ResultSet rz = obx.GetData("SELECT SUM(Balance) AS BL FROM accounts");
            String total_bal = "";

            try{
                while(rz.next()){
                    total_bal = rz.getString("BL");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            JLabel label = new JLabel("Total Amount: "+total_bal+" Tk",SwingConstants.CENTER);
            label.setFont(ob2.customFont3);
            label.setForeground(Color.WHITE);


            newPanel.add(label);


            ob1.mainpanel.add(ob2.label);
            ob1.mainpanel.add(newPanel);
            ob1.mainpanel.add(ob2.image_label);
            ob1.frame.setVisible(true);

        }

}
