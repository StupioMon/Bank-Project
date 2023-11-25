package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDashboard {

        String admin_name,profile_pic;
        int admin_id,login_Id;

        EmployeeDashboard(int Identity_ID,int log_id){
            admin_id=Identity_ID;
            login_Id = log_id;

            CustomModelMon ob = new CustomModelMon();

            String query = "SELECT * FROM employee where Employee_ID='"+admin_id+"'";
            String query2 = "SELECT * FROM login where Login_ID='"+log_id+"'";

            ResultSet rzm = ob.GetData(query);
            ResultSet rzm2 = ob.GetData(query2);

            try{
                while(rzm.next()){
                    admin_name = rzm.getString("Name");
                }
                while(rzm2.next()){
                    profile_pic = rzm2.getString("Profile_Picture");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
        Color DashBoradBgColor = new Color(19,26,43);
        Color DashBoradCardColor = new Color(31,41,64);


        public static void main(String[] args) {
//            EmployeeDashboard ob = new EmployeeDashboard(34,34);
//            ob.loadAdminDashboard();

        }


        public void loadAdminDashboard(){
            CreateWindow dashWindow = new CreateWindow(10,10,1350,700);
            UsedImages obimg = new UsedImages();

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

            String dir = System.getProperty("user.dir");
            String adminPro_Pic = dir+"\\src\\uploads\\"+profile_pic;


            ob1.CreateRadiusPanel(0,10,250,688);
            ob2.CreateResizeImage(50,75,150,150,adminPro_Pic);
            ob1.CreateImage(50,75,150,150,obimg.profile_cover);



            JPanel textPanel = new JPanel();
            textPanel.setBounds(0,220,250,50);
            textPanel.setBackground(ob1.DashBoradCardColor);

            JLabel userName = new JLabel(admin_name,SwingConstants.CENTER);
            userName.setFont(ob1.customFont4);
            userName.setForeground(Color.WHITE);


            ob3.CreateImage(85,350,100,100, obimg.admins);

            JPanel textPanel2 = new JPanel();
            textPanel2.setBounds(0,470,250,50);
            textPanel2.setBackground(ob1.DashBoradCardColor);

            JLabel balance_label = new JLabel("Employee Panel",SwingConstants.CENTER);
            balance_label.setForeground(Color.white);
            balance_label.setFont(ob1.customFont3);

            textPanel.add(userName);
            textPanel2.add(balance_label);

            ob1.CreateButton(75,600,100,30,"Logout");
            ob1.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dashWindow.frame.setVisible(false);
                    LoginAdmin ob = new LoginAdmin();
                    ob.loadAdmin();
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


            ob9.CreateImage(20,30,60,60, obimg.sound_wave);

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




            ob2.CreateHoverButton(320,250,200,160,"Accounts",obimg.user);
            ob6.CreateHoverButton(550,250,200,160,"Deposit Amount",obimg.deposit);
            ob7.CreateHoverButton(775,250,200,160,"Withdraw Amount",obimg.withdraw);
            ob8.CreateHoverButton(1000,250,200,160,"Transfer Amount",obimg.money);

            ob9.CreateHoverButton(320,435,200,160,"Transaction History",obimg.bank_statement);



            ob2.hpanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    AccountsList ob = new AccountsList();
                    ob.loadAccounts();
                }
            });


            ob6.hpanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    DepositAdmin ob = new DepositAdmin();
                    ob.loadDeposit();
                }
            });

            ob7.hpanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    WithdrawAdmin ob = new WithdrawAdmin();
                    ob.loadWithdraw();
                }
            });

            ob8.hpanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TransferAdmin ob = new TransferAdmin();
                    ob.loadTransfer();
                }
            });

            ob9.hpanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TransactionAdmin ob = new TransactionAdmin();
                    ob.loadTransactions();
                }
            });











            JLabel titleLabel = new JLabel("Smartie Commercial Bank");
            titleLabel.setBounds(10,1,300,30);
            titleLabel.setForeground(Color.white);
            dashWindow.panel.add(titleLabel);



            dashWindow.mainpanel.add(ob2.hpanel);
            dashWindow.mainpanel.add(ob6.hpanel);
            dashWindow.mainpanel.add(ob7.hpanel);
            dashWindow.mainpanel.add(ob8.hpanel);
            dashWindow.mainpanel.add(ob9.hpanel);
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
