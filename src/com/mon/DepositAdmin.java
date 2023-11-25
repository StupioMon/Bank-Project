package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepositAdmin {

        int startTransaction=55316460;

        public static void main(String[] args) {
            DepositAdmin ob = new DepositAdmin();
            ob.loadDeposit();
        }

        public void loadDeposit(){
            CreateWindow dashWindow = new CreateWindow(150,10,1100,700);
            UsedImages obimg = new UsedImages();

            CustomModelMon ob1 = new CustomModelMon();
            CustomModelMon ob2 = new CustomModelMon();
            CustomModelMon ob3 = new CustomModelMon();
            CustomModelMon ob4 = new CustomModelMon();
            CustomModelMon ob5 = new CustomModelMon();

            ob1.CreateLabel(50,40,200,50,"Accounts",ob1.customFont3);
            ob1.label.setForeground(Color.white);

            ob1.CreateTextfieldFirst(50,90,250,35,"Account No.");
            ob1.textField.setBackground(ob1.DashBoradCardColor);
            ob1.textField.setForeground(Color.WHITE);
            ob1.textField.setCaretColor(Color.WHITE);

            ob3.CreateTextfield(50,480,250,35,"Account No.");
            ob3.textField.setBackground(ob1.DashBoradCardColor);
            ob3.textField.setForeground(Color.WHITE);
            ob3.textField.setCaretColor(Color.WHITE);

            ob4.CreateTextfield(50,530,250,35,"Amount");
            ob4.textField.setBackground(ob1.DashBoradCardColor);
            ob4.textField.setForeground(Color.WHITE);
            ob4.textField.setCaretColor(Color.WHITE);


            ob2.CreateButtonColors(50,580,120,35,"Deposit",new Color(144, 12, 63));
            ob2.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    ValidatorMon obv= new ValidatorMon();

                    if(obv.isValidAmount(ob3.textField.getText())==1){
                        if(obv.isValidAmount(ob4.textField.getText())==1){


                            int acc_no = Integer.valueOf(ob3.textField.getText());
                            int amount = Integer.valueOf(ob4.textField.getText());
                            int user_ids=0;

                            String namef="";
                            int balance=0;

                            ResultSet rz = ob2.GetData("SELECT * FROM accounts WHERE Account_No='"+acc_no+"'");


                            try{
                                while(rz.next()){
                                    user_ids = Integer.valueOf(rz.getString("User_ID"));
                                    balance = Integer.valueOf(rz.getString("Balance"));
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                            ResultSet rz2 = ob2.GetData("SELECT * FROM user WHERE User_ID='"+user_ids+"'");

                            try{
                                while(rz2.next()){
                                    namef = rz2.getString("Name");
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                            int check = JOptionPane.showConfirmDialog(null,"Are you sure to deposite the account - "+namef,"Confirm Deletion",JOptionPane.YES_NO_OPTION);

                            if(check==0){
                                balance=balance+amount;
                                String tranCountQuery = "SELECT * FROM count_table";
                                ResultSet rt = ob2.GetData(tranCountQuery);
                                int transactionCount=0;
                                try{
                                    while(rt.next()){
                                        transactionCount = Integer.valueOf(rt.getString("transaction"));
                                    }
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }

                                String transactionQuery = "INSERT INTO transaction (Transaction_ID,Account_No,Date,Deposit,Withdraw,Available_Balance) VALUES('"+(startTransaction+transactionCount)+"','"+acc_no+"',curdate(),'"+amount+"',0,'"+balance+"')";
                                int transactionStatus = ob2.SendData(transactionQuery);
                                if(transactionStatus==1){
                                    String updateBalanceQuery = "UPDATE accounts SET Balance='"+balance+"' WHERE Account_No='"+acc_no+"'";
                                    int updateBalanceStatus = ob2.SendData(updateBalanceQuery);
                                    if(updateBalanceStatus==1){
                                        JOptionPane.showMessageDialog(null,"Transaction ID - "+(startTransaction+transactionCount),"Deposit Successful",JOptionPane.PLAIN_MESSAGE);
                                        String CountTranQuery = "UPDATE count_table SET transaction='"+(transactionCount+1)+"' WHERE count_id=1";
                                        int updateTranCountStatus = ob2.SendData(CountTranQuery);
                                        if(updateTranCountStatus==1){
                                            ob1.scrollPane.setVisible(false);
                                            String Query = "SELECT Account_No,A.User_ID,Name,Gender,Contact,Balance FROM accounts as A,user as U,login as l WHERE A.User_ID=U.User_ID AND A.User_ID=l.Identity_ID AND Status='Approved';";
                                            ResultSet rz3 = ob1.GetData(Query);
                                            ob1.CreateTable(50,140,1000,300,rz3);
                                            dashWindow.mainpanel.add(ob1.scrollPane);
                                            ob1.scrollPane.setVisible(true);
                                        }
                                    }
                                }
                            }


                        }else{
                            JOptionPane.showMessageDialog(null,"Enter Valid Amount","Information Alert",JOptionPane.INFORMATION_MESSAGE);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"Enter Valid Account No","Information Alert",JOptionPane.INFORMATION_MESSAGE);
                    }


                }
            });



            ob1.CreateButtonColors(310,90,120,35,"Search",new Color( 25, 111, 61 ));
            ob1.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String searchKey = ob1.textField.getText();
                    if(searchKey.equals("")){
                        ob1.scrollPane.setVisible(false);
                        String Query = "SELECT Account_No,A.User_ID,Name,Gender,Contact,Balance FROM accounts as A,user as U,login as l WHERE A.User_ID=U.User_ID AND A.User_ID=l.Identity_ID AND Status='Approved';";
                        ResultSet rz = ob1.GetData(Query);
                        ob1.CreateTable(50,140,1000,300,rz);
                        dashWindow.mainpanel.add(ob1.scrollPane);
                        ob1.scrollPane.setVisible(true);
                    }
                    else{
                        int acc_no = Integer.valueOf(searchKey);
                        ob1.scrollPane.setVisible(false);
                        String Query = "SELECT Account_No,A.User_ID,Name,Gender,Contact,Balance FROM accounts as A,user as U,login as l WHERE A.User_ID=U.User_ID AND A.User_ID=l.Identity_ID AND Status='Approved' AND Account_No='"+acc_no+"'";
                        ResultSet rz = ob1.GetData(Query);
                        ob1.CreateTable(50,140,1000,300,rz);
                        dashWindow.mainpanel.add(ob1.scrollPane);
                        ob1.scrollPane.setVisible(true);

                    }
                }
            });


            String Query = "SELECT Account_No,A.User_ID,Name,Gender,Contact,Balance FROM accounts as A,user as U,login as l WHERE A.User_ID=U.User_ID AND A.User_ID=l.Identity_ID AND Status='Approved'";
            ResultSet rz = ob1.GetData(Query);
            ob1.CreateTable(50,140,1000,300,rz);
            dashWindow.mainpanel.add(ob1.scrollPane);



            dashWindow.mainpanel.add(ob1.label);
            dashWindow.mainpanel.add(ob1.textField);
            dashWindow.mainpanel.add(ob3.textField);
            dashWindow.mainpanel.add(ob4.textField);
            dashWindow.mainpanel.add(ob1.button);
            dashWindow.mainpanel.add(ob2.button);



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
