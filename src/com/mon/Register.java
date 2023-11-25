package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register {

    File f;
    String filename;
    int startUser = 7710;
    int startAccount = 997710;
    int startLogin = 17120;

    public static void main(String[] args) {
        Register ob = new Register();
        ob.loadRegister();
    }
    public void loadRegister(){

        CreateWindow ob1 = new CreateWindow(250,50,900,650);
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
        CustomModelMon ob5 = new CustomModelMon();
        CustomModelMon ob6 = new CustomModelMon();

        ob2.CreateLabel(100,60,300,50,"Account Opening Form",ob2.customFont4);
        ob2.label.setForeground(Color.WHITE);

        String[] acc_type = {"--Account Type--","Savings Account","Business Account"};


        ob2.CreateCombo(100,130,250,35,acc_type);
        ob2.box.setBackground(ob2.darkBackPanel);
        ob2.box.setForeground(Color.WHITE);

        ob2.CreateTextfield(100,180,250,35,"Full Name");
        ob2.textField.setBackground(ob2.darkBackPanel);
        ob2.textField.setForeground(Color.WHITE);
        ob2.textField.setCaretColor(Color.WHITE);

        ob3.CreateTextfield(100,230,250,35,"Email");
        ob3.textField.setBackground(ob2.darkBackPanel);
        ob3.textField.setForeground(Color.WHITE);
        ob3.textField.setCaretColor(Color.WHITE);

        ob4.CreateTextfield(100,280,250,35,"New Password");
        ob4.textField.setBackground(ob2.darkBackPanel);
        ob4.textField.setForeground(Color.WHITE);
        ob4.textField.setCaretColor(Color.WHITE);

        ob5.CreateTextfield(100,330,250,35,"Contact No.");
        ob5.textField.setBackground(ob2.darkBackPanel);
        ob5.textField.setForeground(Color.WHITE);
        ob5.textField.setCaretColor(Color.WHITE);

        ob4.CreateLabel(100,380,200,30,"Date of Birth",ob5.customFont3);
        ob4.label.setForeground(Color.white);

        ob5.CreateDatePicker(100,410,250,30);
        ob5.datePanel.setBackground(ob5.darkBack);

        ob5.CreateLabel(100,450,200,30,"Gender",ob5.customFont3);
        ob5.label.setForeground(Color.white);

        ButtonGroup gender = new ButtonGroup();
        JRadioButton button1 = new JRadioButton("Male");
        JRadioButton button2 = new JRadioButton("Female");
        button1.setBackground(ob5.darkBack);
        button2.setBackground(ob5.darkBack);
        button1.setForeground(Color.white);
        button2.setForeground(Color.white);
        button1.setBounds(100,475,70,30);
        button2.setBounds(180,475,70,30);
        gender.add(button1);
        gender.add(button2);

        JCheckBox license = new JCheckBox("I agree with all terms and conditions.");
        license.setBounds(100,500,500,50);
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






        ob3.CreateButton(100,560,150,30,"Submit Form");
        ob3.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ac_type = ob2.box.getSelectedItem().toString();
                String full_name = ob2.textField.getText();
                String email = ob3.textField.getText();
                String password = ob4.textField.getText();
                String contact = ob5.textField.getText();

                String day = ob5.day.getSelectedItem().toString();
                String month = ob5.month.getSelectedItem().toString();
                String year = ob5.year.getSelectedItem().toString();

                String[] months = {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

                String monthNum = "";
                for (int x = 1; x < months.length; x++) {
                    if (months[x] == month) {
                        monthNum = String.valueOf(x);
                    }
                }

                String date = year + "-" + monthNum + "-" + day;

                String gender = "";
                if (button1.isSelected()) {
                    gender = "Male";
                }
                if (button2.isSelected()) {
                    gender = "Female";
                }

                String address = ob9.textField.getText();

                ValidatorMon ob = new ValidatorMon();

                if (ob.isValidName(full_name) == 1 && !full_name.isEmpty() && !address.isEmpty() && !password.isEmpty() && !gender.equals("")) {
                    if (ob.isValidEmail(email) == 1) {
                        if (ob.isValidContact(contact) == 1) {


                            String dir = System.getProperty("user.dir");
                            String saveDir = dir + "\\src\\uploads\\";

                            File sourcefile = new File(filename);
                            File destfile = new File(saveDir + contact + f.getName());

                            String profile_pic = contact + f.getName();

                            int min_balance = 2000;
                            int countIndex = 0;
                            int countLogin = 0;

                            ResultSet rzx = ob2.GetData("select user from count_table where count_id=1");
                            ResultSet rzz = ob2.GetData("select login from count_table where count_id=1");
                            try {
                                while (rzx.next()) {
                                    String val = rzx.getString("user").toString();
                                    countIndex = Integer.valueOf(val);
                                }
                                while (rzz.next()) {
                                    String val = rzz.getString("login").toString();
                                    countLogin = Integer.valueOf(val);
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }


                            int UserInfoSendStatus = 0, LoginInfoSendStatus = 0, AccountInfoSendInfo = 0;

                            String UserQuery = "INSERT INTO user (User_ID,Name,Gender,Contact,Address,DOB) VALUES('" + (startUser + countIndex) + "','" + full_name + "','" + gender + "','" + contact + "','" + address + "','" + date + "')";

                            UserInfoSendStatus = ob2.SendData(UserQuery);
                            String pending = "pending";

                            String LoginQuery = "INSERT INTO login (Login_ID,Identity_ID,Role_ID,Username,Password,Profile_picture,Status) VALUES('" + (startLogin + countLogin) + "','" + (startUser + countIndex) + "',3,'" + email + "','" + password + "','" + profile_pic + "','" + pending + "')";
                            LoginInfoSendStatus = ob2.SendData(LoginQuery);

                            String acc_types = "Savings Account";
                            String AccountQuery = "INSERT INTO accounts (Account_No,User_ID,Account_Type,Balance) VALUES('" + (startAccount + countIndex) + "','" + (startUser + countIndex) + "','" + acc_types + "','" + min_balance + "')";
                            AccountInfoSendInfo = ob2.SendData(AccountQuery);

                            if (UserInfoSendStatus == 1 && LoginInfoSendStatus == 1 && AccountInfoSendInfo == 1) {

                                String countQuery = "UPDATE count_table SET user='" + (countIndex + 1) + "' WHERE count_id=1";
                                String countLogQuery = "UPDATE count_table SET login='" + (countLogin + 1) + "' WHERE count_id=1";
                                int res = ob2.SendData(countQuery);
                                int res2 = ob2.SendData(countLogQuery);

                                try {
                                    Files.copy(sourcefile.toPath(), destfile.toPath());
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                JOptionPane.showMessageDialog(null, "Wait for the admin approval. Thank you.", "Your Form is Submitted", JOptionPane.PLAIN_MESSAGE);

                            }


                        }


                    else {
                        JOptionPane.showMessageDialog(null, "Enter a Valid Contact Number.", "Suggestion Box", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                        JOptionPane.showMessageDialog(null, "Enter a Valid Email..", "Suggestion Box", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Enter a Valid Name.","Suggestion Box",  JOptionPane.INFORMATION_MESSAGE);
                }

            }





        });




        ob1.mainpanel.add(ob2.label);
        ob1.mainpanel.add(ob2.box);
        ob1.mainpanel.add(ob2.textField);
        ob1.mainpanel.add(ob3.textField);
        ob1.mainpanel.add(ob4.textField);
        ob1.mainpanel.add(ob5.textField);
        ob1.mainpanel.add(ob9.textField);
        ob1.mainpanel.add(ob5.datePanel);
        ob1.mainpanel.add(ob4.label);
        ob1.mainpanel.add(ob5.label);
        ob1.mainpanel.add(license);
        ob1.mainpanel.add(button1);
        ob1.mainpanel.add(button2);
        ob1.mainpanel.add(ob5.button);
        ob1.mainpanel.add(ob3.button);


        ob1.frame.setVisible(true);

    }

}
