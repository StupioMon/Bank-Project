package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class TransactionAdmin {

        public static void main(String[] args) {
            TransactionAdmin ob = new TransactionAdmin();
            ob.loadTransactions();
        }

        public void loadTransactions(){
            CreateWindow dashWindow = new CreateWindow(150,10,1100,700);
            UsedImages obimg = new UsedImages();

            CustomModelMon ob1 = new CustomModelMon();
            CustomModelMon ob2 = new CustomModelMon();
            CustomModelMon ob3 = new CustomModelMon();
            CustomModelMon ob4 = new CustomModelMon();
            CustomModelMon ob5 = new CustomModelMon();

            ob1.CreateLabel(50,40,200,50,"Transactions",ob1.customFont3);
            ob1.label.setForeground(Color.white);

            ob1.CreateTextfieldFirst(50,90,250,35,"Account Number");
            ob1.textField.setBackground(ob1.DashBoradCardColor);
            ob1.textField.setForeground(Color.WHITE);
            ob1.textField.setCaretColor(Color.WHITE);

            ob3.CreateTextfield(50,480,250,35,"Account No.");
            ob3.textField.setBackground(ob1.DashBoradCardColor);
            ob3.textField.setForeground(Color.WHITE);
            ob3.textField.setCaretColor(Color.WHITE);


            ob1.CreateButtonColors(310,90,120,35,"Search",new Color( 25, 111, 61 ));
            ob1.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String searchKey = ob1.textField.getText();
                    if(searchKey.equals("")){
                        ob1.scrollPane.setVisible(false);
                        String Query = "SELECT * FROM transaction";
                        ResultSet rz = ob1.GetData(Query);
                        ob1.CreateTable(50,140,1000,500,rz);
                        dashWindow.mainpanel.add(ob1.scrollPane);
                        ob1.scrollPane.setVisible(true);
                    }
                    else{
                        int acc_no = Integer.valueOf(searchKey);
                        ob1.scrollPane.setVisible(false);
                        String Query = "SELECT * FROM transaction WHERE Account_No='"+acc_no+"'";
                        ResultSet rz = ob1.GetData(Query);
                        ob1.CreateTable(50,140,1000,500,rz);
                        dashWindow.mainpanel.add(ob1.scrollPane);
                        ob1.scrollPane.setVisible(true);

                    }
                }
            });


            String Query = "SELECT * FROM transaction";
            ResultSet rz = ob1.GetData(Query);
            ob1.CreateTable(50,140,1000,500,rz);
            dashWindow.mainpanel.add(ob1.scrollPane);



            dashWindow.mainpanel.add(ob1.label);
            dashWindow.mainpanel.add(ob1.textField);
            dashWindow.mainpanel.add(ob1.button);


            JLabel titleLabel = new JLabel("Smartie Commercial Bank");
            titleLabel.setBounds(10,1,300,30);
            titleLabel.setForeground(Color.white);
            dashWindow.panel.add(titleLabel);

            dashWindow.mainpanel.setBackground(ob1.DashBoradBgColor);
            dashWindow.panel.setBackground(ob1.DashBoradBgColor);
            dashWindow.cross.setBackground(ob1.DashBoradBgColor);
            dashWindow.frame.setVisible(true);

        }


}
