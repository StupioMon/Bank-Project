package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDashboard {

    int User_ID,Login_IDS;
    int total_balance;
    String user_name,profile_pic;
    Color DashBoradBgColor = new Color(19,26,43);
    Color DashBoradCardColor = new Color(31,41,64);

    UserDashboard(int userId,int login_id){
        User_ID = userId;
        Login_IDS = login_id;

        CustomModelMon ob = new CustomModelMon();

        String query = "SELECT * FROM user where User_ID='"+userId+"'";
        String query2 = "SELECT * FROM login where Login_ID='"+login_id+"'";
        String query3 = "SELECT * FROM accounts where User_ID='"+userId+"'";

        ResultSet rzm = ob.GetData(query);
        ResultSet rzm2 = ob.GetData(query2);
        ResultSet rzm3 = ob.GetData(query3);

        try{
            while(rzm.next()){
                user_name = rzm.getString("Name");
            }
            while(rzm2.next()){
                profile_pic = rzm2.getString("Profile_Picture");
            }
            while(rzm3.next()){
                total_balance = Integer.valueOf(rzm3.getString("Balance"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        UserDashboard ob = new UserDashboard(1,1);
//        ob.loadUserDashboard();
    }
    public void loadUserDashboard(){
        CreateWindow dashWindow = new CreateWindow(10,10,1350,700);

        CustomModelMon ob1 = new CustomModelMon();
        CustomModelMon ob2 = new CustomModelMon();
        CustomModelMon ob3 = new CustomModelMon();
        CustomModelMon ob4 = new CustomModelMon();
        CustomModelMon ob5 = new CustomModelMon();
        CustomModelMon ob6 = new CustomModelMon();
        CustomModelMon ob7 = new CustomModelMon();
        CustomModelMon ob8 = new CustomModelMon();
        CustomModelMon ob9 = new CustomModelMon();
        CustomModelMon ob10 = new CustomModelMon();
        CustomModelMon ob11 = new CustomModelMon();
        CustomModelMon ob12 = new CustomModelMon();

        UsedImages obimg = new UsedImages();


        String dir = System.getProperty("user.dir");
        String userPro_Pic = dir+"\\src\\uploads\\"+profile_pic;


        ob1.CreateRadiusPanel(0,10,250,688);
        ob2.CreateResizeImage(50,75,150,150,userPro_Pic);
        ob1.CreateImage(50,75,150,150,obimg.profile_cover);



        JPanel textPanel = new JPanel();
        textPanel.setBounds(0,220,250,50);
        textPanel.setBackground(ob1.DashBoradCardColor);

        JLabel userName = new JLabel(user_name,SwingConstants.CENTER);
        userName.setFont(ob1.customFont4);
        userName.setForeground(Color.WHITE);


        ob3.CreateImage(75,350,100,100,obimg.money_bag);

        JPanel textPanel2 = new JPanel();
        textPanel2.setBounds(0,470,250,50);
        textPanel2.setBackground(ob1.DashBoradCardColor);

        JLabel balance_label = new JLabel("BDT: "+total_balance+"Tk",SwingConstants.CENTER);
        balance_label.setForeground(Color.white);
        balance_label.setFont(ob1.customFont3);

        textPanel.add(userName);
        textPanel2.add(balance_label);

        ob1.CreateButton(75,600,100,30,"Logout");
        ob1.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashWindow.frame.setVisible(false);
                LoginUser ob =  new LoginUser();
                ob.loadUserLogin();
            }
        });

        dashWindow.mainpanel.add(ob1.panel);
        ob1.panel.add(ob1.image_label);
        ob1.panel.add(ob2.image_label);
        ob1.panel.add(ob3.image_label);
        ob1.panel.add(textPanel);
        ob1.panel.add(textPanel2);
        ob1.panel.add(ob1.button);



        ob4.CreateLabel(320,25,200,50,"Dashboard",ob1.customFont3);
        ob4.label.setForeground(Color.white);

        ob5.CreatePanel(320,80,200,120);
        ob5.panel.setBackground(DashBoradCardColor);
        ob5.CreateLabel(20,0,150,50,"5.7%      Interest Rate",ob1.customFont);
        ob5.label.setFont(new Font("Helvetica",Font.BOLD,14));
        ob5.label.setForeground(Color.WHITE);
        ob6.CreateLabel(20,70,150,50,"Savings Account",ob1.customFont2);
        ob6.label.setFont(new Font("Helvetica",Font.BOLD,10));
        ob6.label.setForeground(Color.WHITE);


        ob5.CreateImage(20,30,60,60,obimg.sound_waves);

        ob5.panel.add(ob5.label);
        ob5.panel.add(ob6.label);
        ob5.panel.add(ob5.image_label);

        ob7.CreatePanel(550,80,200,120);
        ob7.panel.setBackground(DashBoradCardColor);
        ob7.CreateLabel(20,0,150,50,"10     Transactions",ob1.customFont);
        ob7.label.setFont(new Font("Helvetica",Font.BOLD,14));
        ob7.label.setForeground(Color.WHITE);
        ob8.CreateLabel(20,70,150,50,"Total Transactions",ob1.customFont2);
        ob8.label.setFont(new Font("Helvetica",Font.BOLD,10));
        ob8.label.setForeground(Color.WHITE);


        ob7.CreateImage(20,30,60,60,obimg.transaction);

        ob7.panel.add(ob7.label);
        ob7.panel.add(ob8.label);
        ob7.panel.add(ob7.image_label);

        ob9.CreatePanel(775,80,200,120);
        ob9.panel.setBackground(DashBoradCardColor);
        ob9.CreateLabel(20,0,150,50,"SWSSWXYNV",ob1.customFont);
        ob9.label.setFont(new Font("Helvetica",Font.BOLD,14));
        ob9.label.setForeground(Color.WHITE);
        ob10.CreateLabel(20,70,150,50,"SWIFT CODE",ob1.customFont2);
        ob10.label.setFont(new Font("Helvetica",Font.BOLD,10));
        ob10.label.setForeground(Color.WHITE);


        ob9.CreateImage(20,30,60,60,obimg.sound_wave);

        ob9.panel.add(ob9.label);
        ob9.panel.add(ob10.label);
        ob9.panel.add(ob9.image_label);

        ob11.CreatePanel(1000,80,200,120);
        ob11.panel.setBackground(DashBoradCardColor);
        ob11.CreateLabel(20,0,150,50,"Location",ob1.customFont);
        ob11.label.setFont(new Font("Helvetica",Font.BOLD,14));
        ob11.label.setForeground(Color.WHITE);
        ob12.CreateLabel(20,70,150,50,"Airport, Dhaka",ob1.customFont2);
        ob12.label.setFont(new Font("Helvetica",Font.BOLD,10));
        ob12.label.setForeground(Color.WHITE);


        ob11.CreateImage(20,32,60,60,obimg.location);

        ob11.panel.add(ob11.label);
        ob11.panel.add(ob12.label);
        ob11.panel.add(ob11.image_label);


        ob2.CreateHoverButton(320,250,200,160,"Deposite Amount",obimg.deposit);
        ob3.CreateHoverButton(550,250,200,160,"Withdraw Amount",obimg.withdraw);
        ob4.CreateHoverButton(775,250,200,160,"Transfer Amount",obimg.money);
        ob5.CreateHoverButton(1000,250,200,160,"Transaction History",obimg.bank_statement);


        ob2.hpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DepositUser ob = new DepositUser(User_ID,Login_IDS,dashWindow.frame);
                ob.loadDeposit();
            }
        });

        ob3.hpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WithdrawUser ob = new WithdrawUser(User_ID,Login_IDS,dashWindow.frame);
                ob.loadWithdraw();
            }
        });

        ob4.hpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TransferUser ob = new TransferUser(User_ID,Login_IDS,dashWindow.frame);
                ob.loadTransfer();
            }
        });

        ob5.hpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TransactionUser ob = new TransactionUser(User_ID,Login_IDS);
                ob.loadTransactions();
            }
        });








        JLabel titleLabel = new JLabel("Smartie Commercial Bank");
        titleLabel.setBounds(10,1,300,30);
        titleLabel.setForeground(Color.white);
        dashWindow.panel.add(titleLabel);



        dashWindow.mainpanel.add(ob2.hpanel);
        dashWindow.mainpanel.add(ob3.hpanel);
        dashWindow.mainpanel.add(ob4.hpanel);
        dashWindow.mainpanel.add(ob5.hpanel);
        dashWindow.mainpanel.add(ob4.label);
        dashWindow.mainpanel.add(ob5.panel);
        dashWindow.mainpanel.add(ob7.panel);
        dashWindow.mainpanel.add(ob9.panel);
        dashWindow.mainpanel.add(ob11.panel);
        dashWindow.mainpanel.setBackground(DashBoradBgColor);
        dashWindow.panel.setBackground(DashBoradBgColor);
        dashWindow.cross.setBackground(DashBoradBgColor);
        dashWindow.frame.setVisible(true);
    }
}
