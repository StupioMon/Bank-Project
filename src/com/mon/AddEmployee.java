package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEmployee {

        File f;
        String filename;
        int startEmployee = 999910;

        int startLogin = 17120;

        public static void main(String[] args) {
           AddEmployee ob = new AddEmployee();
           ob.loadRegister();
        }
        public void loadRegister(){

            CreateWindow ob1 = new CreateWindow(250,50,900,580);
            ob1.cross.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    EmployeeList ob = new EmployeeList();
                    ob.loadEmployee();
                }
            });


            CustomModelMon ob2 = new CustomModelMon();
            CustomModelMon ob3 = new CustomModelMon();
            CustomModelMon ob4 = new CustomModelMon();
            CustomModelMon ob5 = new CustomModelMon();
            CustomModelMon ob6 = new CustomModelMon();

            ob2.CreateLabel(100,60,300,50,"Register Employee",ob2.customFont4);
            ob2.label.setForeground(Color.WHITE);


            ob2.CreateTextfieldFirst(100,150,250,35,"Full Name");
            ob2.textField.setBackground(ob2.darkBackPanel);
            ob2.textField.setForeground(Color.WHITE);
            ob2.textField.setCaretColor(Color.WHITE);

            ob3.CreateTextfield(100,200,250,35,"Email");
            ob3.textField.setBackground(ob2.darkBackPanel);
            ob3.textField.setForeground(Color.WHITE);
            ob3.textField.setCaretColor(Color.WHITE);

            ob4.CreateTextfield(100,250,250,35,"New Password");
            ob4.textField.setBackground(ob2.darkBackPanel);
            ob4.textField.setForeground(Color.WHITE);
            ob4.textField.setCaretColor(Color.WHITE);

            ob5.CreateTextfield(100,300,250,35,"Contact No.");
            ob5.textField.setBackground(ob2.darkBackPanel);
            ob5.textField.setForeground(Color.WHITE);
            ob5.textField.setCaretColor(Color.WHITE);



            ob5.CreateLabel(100,350,200,30,"Gender",ob5.customFont3);
            ob5.label.setForeground(Color.white);

            ButtonGroup gender = new ButtonGroup();
            JRadioButton button1 = new JRadioButton("Male");
            JRadioButton button2 = new JRadioButton("Female");
            button1.setBackground(ob5.darkBack);
            button2.setBackground(ob5.darkBack);
            button1.setForeground(Color.white);
            button2.setForeground(Color.white);
            button1.setBounds(100,375,70,30);
            button2.setBounds(180,375,70,30);
            gender.add(button1);
            gender.add(button2);

            JCheckBox license = new JCheckBox("I agree with all terms and conditions.");
            license.setBounds(100,400,300,50);
            license.setBackground(ob2.darkBack);
            license.setForeground(Color.white);

            CustomModelMon ob9 = new CustomModelMon();

            ob5.CreateButton(600,370,100,30,"Upload Image");

            ob9.CreateTextfield(450,430,410,35,"Address");
            ob9.textField.setBackground(ob2.darkBackPanel);
            ob9.textField.setForeground(Color.WHITE);
            ob9.textField.setCaretColor(Color.WHITE);

            ob5.button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    JFileChooser img_chooser = new JFileChooser();
                    int response = img_chooser.showOpenDialog(null);

                    if(response == img_chooser.APPROVE_OPTION){
                        f = img_chooser.getSelectedFile();
                        filename = f.getAbsolutePath();


                        ob5.CreateResizeImage(580,200,150,150,filename);
                        ob5.image_label.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY));
                        ob1.mainpanel.add(ob5.image_label);
                        ob1.mainpanel.repaint();

                    }

                }
            });






            ob3.CreateButton(100,460,150,30,"Submit Form");
            ob3.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String full_name = ob2.textField.getText();
                    String email = ob3.textField.getText();
                    String password = ob4.textField.getText();
                    String contact = ob5.textField.getText();

                    String gender="";
                    if(button1.isSelected()){
                        gender = "Male";
                    }
                    if(button2.isSelected()){
                        gender = "Female";
                    }



                    ValidatorMon obv = new ValidatorMon();
                    if(obv.isValidName(full_name)==1 && !full_name.isEmpty() && !password.isEmpty() && !gender.equals("")){
                        if(obv.isValidEmail(email)==1){
                            if(obv.isValidContact(contact)==1){


                                String dir = System.getProperty("user.dir");
                                String saveDir = dir+"\\src\\uploads\\";

                                File sourcefile = new File(filename);
                                File destfile =new File(saveDir+contact+f.getName());

                                String profile_pic = contact+f.getName();

                                int countEmp=0;
                                int countLogin=0;

                                ResultSet rzy = ob2.GetData("select employee from count_table where count_id=1");
                                ResultSet rzz = ob2.GetData("select login from count_table where count_id=1");
                                try{

                                    while(rzy.next()){
                                        String val = rzy.getString("employee").toString();
                                        countEmp=Integer.valueOf(val);
                                    }
                                    while(rzz.next()){
                                        String val = rzz.getString("login").toString();
                                        countLogin=Integer.valueOf(val);
                                    }
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }

                                String address = ob9.textField.getText();
                                int UserInfoSendStatus=0,LoginInfoSendStatus=0,AccountInfoSendInfo=0;

                                String UserQuery = "INSERT INTO employee (Employee_ID,Name,Gender,Contact,Address) VALUES('"+(startEmployee+countEmp)+"','"+full_name+"','"+gender+"','"+contact+"','"+address+"')";

                                UserInfoSendStatus = ob2.SendData(UserQuery);
                                String pending = "Approved";

                                String LoginQuery = "INSERT INTO login (Login_ID,Identity_ID,Role_ID,Username,Password,Profile_picture,Status) VALUES('"+(startLogin+countLogin)+"','"+(startEmployee+countEmp)+"',2,'"+email+"','"+password+"','"+profile_pic+"','"+pending+"')";
                                LoginInfoSendStatus = ob2.SendData(LoginQuery);


                                if(UserInfoSendStatus==1 && LoginInfoSendStatus==1){
                                    String countQuery="UPDATE count_table SET employee='"+(countEmp+1)+"' WHERE count_id=1";
                                    String countLogQuery="UPDATE count_table SET login='"+(countLogin+1)+"' WHERE count_id=1";
                                    int res = ob2.SendData(countQuery);
                                    int res2 = ob2.SendData(countLogQuery);
                                    try {
                                        Files.copy(sourcefile.toPath(),destfile.toPath());
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    JOptionPane.showMessageDialog(null,"ID is Created. Thank you.","Your Form is Submitted",JOptionPane.PLAIN_MESSAGE);

                                }


                            }
                            else {
                                JOptionPane.showMessageDialog(null,"Enter a Contact Number.","Suggestion Box",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Enter a Valid email.","Suggestion Box",JOptionPane.INFORMATION_MESSAGE);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"Enter a Valid Name.","Suggestion Box",JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            });




            ob1.mainpanel.add(ob2.label);
            ob1.mainpanel.add(ob2.textField);
            ob1.mainpanel.add(ob3.textField);
            ob1.mainpanel.add(ob4.textField);
            ob1.mainpanel.add(ob5.textField);
            ob1.mainpanel.add(ob9.textField);
            ob1.mainpanel.add(ob5.label);
            ob1.mainpanel.add(license);
            ob1.mainpanel.add(button1);
            ob1.mainpanel.add(button2);
            ob1.mainpanel.add(ob5.button);
            ob1.mainpanel.add(ob3.button);


            ob1.frame.setVisible(true);

        }



}
