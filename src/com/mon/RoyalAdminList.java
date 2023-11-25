package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoyalAdminList {



        public static void main(String[] args) {
            RoyalAdminList ob = new RoyalAdminList();
            ob.loadRoyalAdmin();
        }

        public void loadRoyalAdmin(){
            CreateWindow dashWindow = new CreateWindow(150,10,1100,700);
            UsedImages obimg = new UsedImages();

            CustomModelMon ob1 = new CustomModelMon();
            CustomModelMon ob2 = new CustomModelMon();
            CustomModelMon ob3 = new CustomModelMon();
            CustomModelMon ob4 = new CustomModelMon();
            CustomModelMon ob5 = new CustomModelMon();

            ob1.CreateLabel(50,40,200,50,"Royal Admins",ob1.customFont3);
            ob1.label.setForeground(Color.white);

            ob1.CreateTextfield(50,90,250,35,"Search");
            ob1.textField.setBackground(ob1.DashBoradCardColor);
            ob1.textField.setForeground(Color.WHITE);
            ob1.textField.setCaretColor(Color.WHITE);

            ob2.CreateButtonColors(440,90,120,35,"Add Admin",new Color(144, 12, 63));
            ob2.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dashWindow.frame.setVisible(false);
                    AddAdmin ob = new AddAdmin();
                    ob.loadRegister();
                }
            });

            ob3.CreateButtonColors(570,90,120,35,"Update Admin",new Color(199, 0, 57 ));
            ob3.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String searchKey = ob1.textField.getText();
                    if(!searchKey.equals("")){
                        dashWindow.frame.setVisible(false);
                        UpdateAdmin ob = new UpdateAdmin(Integer.valueOf(searchKey));
                        ob.loadUpdate();
                    }else{
                        JOptionPane.showMessageDialog(null,"No Admin is selected","Message",JOptionPane.PLAIN_MESSAGE);
                    }

                }
            });


            ob4.CreateButtonColors(700,90,120,35,"Delete Admin",new Color(255, 87, 51));
            ob4.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String searchKey = ob1.textField.getText();
                    if(!searchKey.equals("")){

                        int user_id=0;
                        int delete_id = Integer.valueOf(searchKey);
                        String del_name="";

                        ResultSet rz2 = ob1.GetData("SELECT * from admin WHERE Admin_ID='"+delete_id+"'");

                        try {
                            while(rz2.next()){
                                del_name = rz2.getString("name");
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);

                        }

                        int check = JOptionPane.showConfirmDialog(null,"Are you sure to delete the Admin - "+del_name,"Confirm Deletion",JOptionPane.YES_NO_OPTION);
                        System.out.println(check);
                        if(check==0){

                            int delReqLoginStatus = ob1.SendData("DELETE FROM login WHERE Identity_ID='"+delete_id+"'");
                            int delReqUserStatus = ob1.SendData("DELETE FROM admin WHERE Admin_ID='"+delete_id+"'");

                            if(delReqLoginStatus==1 && delReqUserStatus==1){
                                JOptionPane.showMessageDialog(null,"Successfully Deleted Admin","Deletion Successful",JOptionPane.PLAIN_MESSAGE);
                                dashWindow.frame.setVisible(false);
                                RoyalAdminList ob = new RoyalAdminList();
                                ob.loadRoyalAdmin();
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Can't Delete Admin","Deletion Unsuccessful",JOptionPane.ERROR_MESSAGE);

                            }
                        }



                    }else{
                        JOptionPane.showMessageDialog(null,"No Admin is selected","Alert Message",JOptionPane.PLAIN_MESSAGE);
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
                        String Query = "SELECT Admin_ID,Name,Username,Gender,Contact,Address FROM admin as a,login as l WHERE a.Admin_ID=l.Identity_ID";
                        ResultSet rz = ob1.GetData(Query);
                        ob1.CreateTable(50,140,1000,500,rz);
                        dashWindow.mainpanel.add(ob1.scrollPane);
                        ob1.scrollPane.setVisible(true);
                    }
                    else{
                        int admin_id = Integer.valueOf(searchKey);
                        ob1.scrollPane.setVisible(false);
                        String Query = "SELECT Admin_ID,Name,Username,Gender,Contact,Address FROM admin as a,login as l WHERE a.Admin_ID=l.Identity_ID AND Admin_ID='"+admin_id+"'";
                        ResultSet rz = ob1.GetData(Query);
                        ob1.CreateTable(50,140,1000,500,rz);
                        dashWindow.mainpanel.add(ob1.scrollPane);
                        ob1.scrollPane.setVisible(true);

                    }
                }
            });


            String Query = "SELECT Admin_ID,Name,Username,Gender,Contact,Address FROM admin as a,login as l WHERE a.Admin_ID=l.Identity_ID";
            ResultSet rz = ob1.GetData(Query);
            ob1.CreateTable(50,140,1000,500,rz);
            dashWindow.mainpanel.add(ob1.scrollPane);



            dashWindow.mainpanel.add(ob1.label);
            dashWindow.mainpanel.add(ob1.textField);
            dashWindow.mainpanel.add(ob1.button);
            dashWindow.mainpanel.add(ob2.button);
            dashWindow.mainpanel.add(ob3.button);
            dashWindow.mainpanel.add(ob4.button);


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
