package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAdmin {

        public static void main(String[] args) {
            LoginAdmin ob = new LoginAdmin();
            ob.loadAdmin();
        }

        public void loadAdmin(){
            CreateWindow ob1 = new CreateWindow(400,200,480,340);
            ob1.cross.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    HomeScreen home = new HomeScreen();
                    home.CreateScreen();
                }
            });

            CustomModelMon ob2 = new CustomModelMon();
            CustomModelMon ob3 = new CustomModelMon();
            CustomModelMon ob4 = new CustomModelMon();

            ob2.CreateLabel(180,25,400,70,"Login Admin !",ob2.customFont4);
            ob2.label.setForeground(Color.white);

            ob3.CreateTextfieldFirst(120,120,250,35,"Email");
            ob3.textField.setBackground(ob2.darkBackPanel);
            ob3.textField.setForeground(Color.WHITE);
            ob3.textField.setCaretColor(Color.WHITE);

            ob4.CreateTextfield(120,170,250,35,"New Password");
            ob4.textField.setBackground(ob2.darkBackPanel);
            ob4.textField.setForeground(Color.WHITE);
            ob4.textField.setCaretColor(Color.WHITE);

            ob3.CreateButton(120,230,250,30,"Login");
            ob3.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String user = ob3.textField.getText();
                    String pass = ob4.textField.getText();

                    String queryLog = "SELECT * FROM login WHERE Username='"+user+"' AND Password='"+pass+"'";
                    ResultSet rz = ob3.GetData(queryLog);

                    int logStatus=0;
                    int Identity_ID=0;
                    int login_id=0;

                    try{
                        while(rz.next()){
                            Identity_ID = Integer.valueOf(rz.getString("Identity_ID"));
                            String role = rz.getString("Role_ID");
                            login_id = Integer.valueOf(rz.getString("Login_ID"));
                            if(role.equals("1")){
                                logStatus=1;
                            }else if(role.equals("2")){
                                logStatus=2;
                            }
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"Username or Password is not valid.","Login Failed",JOptionPane.PLAIN_MESSAGE);
                        throw new RuntimeException(ex);
                    }

                    if(logStatus!=0){
                        if(logStatus==1){
                            ob1.frame.setVisible(false);
                            AdminDashboard obA = new AdminDashboard(Identity_ID,login_id);
                            obA.loadAdminDashboard();
                        }else if(logStatus==2){
                            ob1.frame.setVisible(false);
                            EmployeeDashboard ob = new EmployeeDashboard(Identity_ID,login_id);
                            ob.loadAdminDashboard();
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"No Admin Found.","Login Failed",JOptionPane.PLAIN_MESSAGE);

                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null,"No Admin Found.","Login Failed",JOptionPane.PLAIN_MESSAGE);

                    }


                }
            });



            ob1.mainpanel.add(ob2.label);
            ob1.mainpanel.add(ob3.textField);
            ob1.mainpanel.add(ob4.textField);
            ob1.mainpanel.add(ob3.button);
            ob1.frame.setVisible(true);

        }
    }


