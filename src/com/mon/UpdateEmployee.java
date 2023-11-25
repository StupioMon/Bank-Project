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

public class UpdateEmployee {

        File f;
        int isFileUpdated=0;
        String filename;
        int Emp_ID,Login_ID;
        String name,gender,username,password,contact,address,profile_pics;




        UpdateEmployee(int emp_id){
            Emp_ID = emp_id;
            CustomModelMon ob = new CustomModelMon();
            String ac_query = "SELECT Login_ID,Name,Gender,Contact,Address,Profile_picture,Username,Password from employee as e,login as l WHERE e.Employee_ID=l.Identity_ID AND Employee_ID='"+Emp_ID+"'";
            ResultSet rz1 = ob.GetData(ac_query);

            try {
                while (rz1.next()){
                    name = rz1.getString("Name");
                    gender = rz1.getString("Gender");
                    username = rz1.getString("Username");
                    password = rz1.getString("Password");
                    contact = rz1.getString("Contact");
                    address = rz1.getString("Address");
                    profile_pics = rz1.getString("Profile_Picture");
                    Login_ID = Integer.valueOf(rz1.getString("Login_ID"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        public static void main(String[] args) {
            UpdateEmployee ob = new UpdateEmployee(10);
            ob.loadUpdate();
        }
        public void loadUpdate(){

            CreateWindow ob1 = new CreateWindow(250,50,900,650);
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
            CustomModelMon ob7 = new CustomModelMon();
            CustomModelMon ob8 = new CustomModelMon();

            ob2.CreateLabel(100,60,300,50,"Account Update Form",ob2.customFont4);
            ob2.label.setForeground(Color.WHITE);


            ob6.CreateTextfieldUpdate(100,130,250,35,String.valueOf(Emp_ID));
            ob6.textField.setBackground(ob2.darkBackPanel);
            ob6.textField.setForeground(Color.WHITE);
            ob6.textField.setFocusable(false);

            ob2.CreateTextfieldUpdate(100,180,250,35,name);
            ob2.textField.setBackground(ob2.darkBackPanel);
            ob2.textField.setForeground(Color.WHITE);
            ob2.textField.setCaretColor(Color.WHITE);

            ob3.CreateTextfieldUpdate(100,230,250,35,username);
            ob3.textField.setBackground(ob2.darkBackPanel);
            ob3.textField.setForeground(Color.WHITE);
            ob3.textField.setCaretColor(Color.WHITE);

            ob4.CreateTextfieldUpdate(100,280,250,35,password);
            ob4.textField.setBackground(ob2.darkBackPanel);
            ob4.textField.setForeground(Color.WHITE);
            ob4.textField.setCaretColor(Color.WHITE);

            ob5.CreateTextfieldUpdate(100,330,250,35,contact);
            ob5.textField.setBackground(ob2.darkBackPanel);
            ob5.textField.setForeground(Color.WHITE);
            ob5.textField.setCaretColor(Color.WHITE);





            ob5.CreateLabel(100,380,200,30,"Gender",ob5.customFont3);
            ob5.label.setForeground(Color.white);

            ob8.CreateTextfieldUpdate(100,410,250,35,gender);
            ob8.textField.setBackground(ob2.darkBackPanel);
            ob8.textField.setForeground(Color.WHITE);
            ob8.textField.setCaretColor(Color.WHITE);




            CustomModelMon ob9 = new CustomModelMon();

            ob5.CreateButton(600,370,100,30,"Change Photo");

            ob9.CreateTextfieldUpdate(450,430,410,35,address);
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

                        ob5.image_label.setVisible(false);


                        ob5.CreateResizeImage(580,200,150,150,filename);
                        ob5.image_label.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY));
                        ob1.mainpanel.add(ob5.image_label);
                        ob5.image_label.setVisible(true);
                        isFileUpdated=1;


                    }


                }
            });

            String dir = System.getProperty("user.dir");
            filename = dir+"\\src\\uploads\\"+profile_pics;


            ob5.CreateResizeImage(580,200,150,150,filename);
            ob5.image_label.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY));
            ob1.mainpanel.add(ob5.image_label);






            ob3.CreateButton(100,560,150,30,"Update");
            ob3.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {



                    String dir = System.getProperty("user.dir");
                    String saveDir = dir+"\\src\\uploads\\";


                    String names = ob2.textField.getText();
                    String user = ob3.textField.getText();
                    String pass = ob4.textField.getText();
                    String contacts = ob5.textField.getText();
                    String genders= ob8.textField.getText();
                    String addresses= ob9.textField.getText();

                    ValidatorMon obv = new ValidatorMon();
                    if(obv.isValidName(names)==1 && !addresses.isEmpty() && !names.isEmpty() && !pass.isEmpty() && !genders.equals("")){
                        if(obv.isValidEmail(user)==1){
                            if(obv.isValidContact(contacts)==1){

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



                    int UserInfoSendStatus=0,LoginInfoSendStatus=0,AccountInfoSendInfo=0;

                    String EmpQuery = "UPDATE employee SET Name='"+names+"',Gender='"+genders+"',Contact='"+contacts+"',Address='"+addresses+"' WHERE Employee_ID='"+Emp_ID+"'";

                    UserInfoSendStatus = ob2.SendData(EmpQuery);
                    String pending = "Approved";

                    String LoginQuery="";

                    if(isFileUpdated==1){
                        profile_pics = contacts+f.getName();
                        LoginQuery = "UPDATE login SET Username='"+user+"',Password='"+pass+"',Profile_picture='"+profile_pics+"' WHERE Login_ID='"+Login_ID+"'";

                    }
                    else{
                        LoginQuery = "UPDATE login SET Username='"+user+"',Password='"+pass+"' WHERE Login_ID='"+Login_ID+"'";

                    }


                    LoginInfoSendStatus = ob2.SendData(LoginQuery);



                    if(UserInfoSendStatus==1 && LoginInfoSendStatus==1){

                        if(isFileUpdated==1){
                            try {
                                File sourcefile = new File(filename);
                                File destfile =new File(saveDir+contacts+f.getName());
                                Files.copy(sourcefile.toPath(),destfile.toPath());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }


                        JOptionPane.showMessageDialog(null,"Your information is updated successfully. Thank you.","Your Form is Submitted",JOptionPane.PLAIN_MESSAGE);

                    }




                }
            });




            ob1.mainpanel.add(ob2.label);
            ob1.mainpanel.add(ob2.textField);
            ob1.mainpanel.add(ob3.textField);
            ob1.mainpanel.add(ob4.textField);
            ob1.mainpanel.add(ob5.textField);
            ob1.mainpanel.add(ob6.textField);
            ob1.mainpanel.add(ob8.textField);
            ob1.mainpanel.add(ob9.textField);
            ob1.mainpanel.add(ob5.label);
            ob1.mainpanel.add(ob5.button);
            ob1.mainpanel.add(ob3.button);


            ob1.frame.setVisible(true);

        }


}
