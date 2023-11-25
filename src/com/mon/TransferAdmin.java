package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferAdmin {

        int startTransaction=55316460;

        public static void main(String[] args) {
            TransferAdmin ob = new TransferAdmin();
            ob.loadTransfer();
        }

        public void loadTransfer(){
            CreateWindow dashWindow = new CreateWindow(150,10,1100,700);
            UsedImages obimg = new UsedImages();

            CustomModelMon ob1 = new CustomModelMon();
            CustomModelMon ob2 = new CustomModelMon();
            CustomModelMon ob3 = new CustomModelMon();
            CustomModelMon ob4 = new CustomModelMon();
            CustomModelMon ob5 = new CustomModelMon();

            ob1.CreateLabel(50,40,200,50,"Transfer Amount",ob1.customFont3);
            ob1.label.setForeground(Color.white);

            ob1.CreateTextfieldFirst(50,90,250,35,"Account Number");
            ob1.textField.setBackground(ob1.DashBoradCardColor);
            ob1.textField.setForeground(Color.WHITE);
            ob1.textField.setCaretColor(Color.WHITE);

            ob3.CreateTextfield(50,480,250,35,"Sender Account No.");
            ob3.textField.setBackground(ob1.DashBoradCardColor);
            ob3.textField.setForeground(Color.WHITE);
            ob3.textField.setCaretColor(Color.WHITE);

            ob4.CreateTextfield(50,580,250,35,"Amount");
            ob4.textField.setBackground(ob1.DashBoradCardColor);
            ob4.textField.setForeground(Color.WHITE);
            ob4.textField.setCaretColor(Color.WHITE);

            ob5.CreateTextfield(50,530,250,35,"Receiver Account No.");
            ob5.textField.setBackground(ob1.DashBoradCardColor);
            ob5.textField.setForeground(Color.WHITE);
            ob5.textField.setCaretColor(Color.WHITE);


            ob2.CreateButtonColors(330,580,120,35,"Transfer",new Color(144, 12, 63));
            ob2.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    ValidatorMon obv= new ValidatorMon();

                    if(obv.isValidAmount(ob3.textField.getText())==1){
                        if(obv.isValidAmount(ob5.textField.getText())==1){
                            if(obv.isValidAmount(ob4.textField.getText())==1){

                                int receiverAcc_no = Integer.valueOf(ob5.textField.getText());
                                int senderAcc_no = Integer.valueOf(ob3.textField.getText());
                                int amount = Integer.valueOf(ob4.textField.getText());
                                int user_ids=0;

                                String namef="";
                                int senderBalance=0;
                                int receiverBalance=0;

                                ResultSet rz = ob2.GetData("SELECT * FROM accounts WHERE Account_No='"+receiverAcc_no+"'");
                                ResultSet rz2 = ob2.GetData("SELECT * FROM accounts WHERE Account_No='"+senderAcc_no+"'");


                                try{
                                    while(rz.next()){
                                        user_ids = Integer.valueOf(rz.getString("User_ID"));
                                        receiverBalance = Integer.valueOf(rz.getString("Balance"));
                                    }
                                    while(rz2.next()){
                                        senderBalance = Integer.valueOf(rz2.getString("Balance"));
                                    }
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }

                                ResultSet rz3 = ob2.GetData("SELECT * FROM user WHERE User_ID='"+user_ids+"'");

                                try{
                                    while(rz3.next()){
                                        namef = rz3.getString("Name");
                                    }
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }

                                int check = JOptionPane.showConfirmDialog(null,"Are you sure to Transfer to account - "+namef,"Confirm Deletion",JOptionPane.YES_NO_OPTION);

                                if(check==0){


                                    if(senderBalance>=amount){
                                        senderBalance = senderBalance-amount;
                                        receiverBalance = receiverBalance+amount;
                                    }


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

                                    String transactionDepQuery = "INSERT INTO transaction (Transaction_ID,Account_No,Date,Deposit,Withdraw,Available_Balance) VALUES('"+(startTransaction+transactionCount)+"','"+senderAcc_no+"',curdate(),0,'"+amount+"','"+senderBalance+"')";
                                    String transactionQuery = "INSERT INTO transaction (Transaction_ID,Account_No,Date,Deposit,Withdraw,Available_Balance) VALUES('"+(startTransaction+transactionCount+1)+"','"+receiverAcc_no+"',curdate(),'"+amount+"',0,'"+receiverBalance+"')";

                                    int transactionDepStatus = ob2.SendData(transactionDepQuery);
                                    int transactionStatus = ob2.SendData(transactionQuery);

                                    if(transactionStatus==1 && transactionDepStatus==1){

                                        String updateDepBalanceQuery = "UPDATE accounts SET Balance='"+senderBalance+"' WHERE Account_No='"+senderAcc_no+"'";
                                        String updateBalanceQuery = "UPDATE accounts SET Balance='"+receiverBalance+"' WHERE Account_No='"+receiverAcc_no+"'";

                                        int updateDepBalanceStatus = ob2.SendData(updateDepBalanceQuery);
                                        int updateBalanceStatus = ob2.SendData(updateBalanceQuery);

                                        if(updateBalanceStatus==1 && updateDepBalanceStatus==1){
                                            JOptionPane.showMessageDialog(null,"Transaction ID - "+(startTransaction+transactionCount),"Withdraw Successful",JOptionPane.PLAIN_MESSAGE);
                                            String CountTranQuery = "UPDATE count_table SET transaction='"+(transactionCount+2)+"' WHERE count_id=1";
                                            int updateTranCountStatus = ob2.SendData(CountTranQuery);
                                            if(updateTranCountStatus==1){
                                                ob1.scrollPane.setVisible(false);
                                                String Query = "SELECT Account_No,A.User_ID,Name,Gender,Contact,Balance FROM accounts as A,user as U,login as l WHERE A.User_ID=U.User_ID AND A.User_ID=l.Identity_ID AND Status='Approved';";
                                                ResultSet rz4 = ob1.GetData(Query);
                                                ob1.CreateTable(50,140,1000,300,rz4);
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
                            JOptionPane.showMessageDialog(null,"Enter Valid Receiver Account No","Information Alert",JOptionPane.INFORMATION_MESSAGE);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"Enter Valid Sender Account No","Information Alert",JOptionPane.INFORMATION_MESSAGE);
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
            dashWindow.mainpanel.add(ob5.textField);
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
