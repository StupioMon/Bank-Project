package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class test {
    public static JFrame frame;
    public static JPanel mainFetchPanel;
    public static ResultSet resultSet;

    public static void main(String[] args) {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1650,1080);
        frame.setTitle("Fetch Data");
        frame.setLayout(null);

        mainFetchPanel = new JPanel();
        mainFetchPanel.setBounds(0,0,1650,1080);
        mainFetchPanel.setBackground(Color.white);
        mainFetchPanel.setLayout(null);

        frame.add(mainFetchPanel);
        LoadFetch();
        frame.setVisible(true);
    }

    public static void LoadFetch(){
        JPanel panel1 = new JPanel();
        panel1.setBounds(0,0,300,1080);
        panel1.setLayout(null);
        panel1.setBackground(new Color(75,0,130));

        JPanel panel2 = new JPanel();
        panel2.setBounds(250,0,1350,1080);
        panel2.setLayout(null);
        panel2.setBackground(Color.WHITE);

        String[][] Values = new String[14][5];

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartie","root","");
            Statement st = con.createStatement();
            resultSet = st.executeQuery("SELECT * FROM accounts");
            int row=0;

            while(resultSet.next()){
                Values[row][0] = resultSet.getString("id").toString();
                Values[row][1] = resultSet.getString("name");
                Values[row][2] = resultSet.getString("age");
                Values[row][3] = resultSet.getString("blood_group");
                Values[row][4] = resultSet.getString("contact");
                row++;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        String[] ColumnNames = {"Id","Name","Age","Blood Group","Contact"};

        JTextField id1 = new JTextField();
        id1.setBounds(150,100,150,40);

        JTextField name1 = new JTextField();
        name1.setBounds(320,100,150,40);

        JButton update = new JButton("Update");
        update.setBounds(480,100,150,40);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idCheck = Integer.parseInt(id1.getText());
                String newName = name1.getText();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smiley","root","root");
                    Statement st = con.createStatement();
                    String command = "UPDATE info SET name='"+newName+"' WHERE id='"+idCheck+"'";
                    st.execute(command);
                    System.out.println("Updated");


                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });


        JLabel label1 = new JLabel();
        label1.setText("DATA FETCH AND UPDATE");
        label1.setFont(new Font("Algerian",Font.BOLD,25));
        label1.setForeground(new Color(75,0,130));
        label1.setBounds(100,10,400,50);


        JTable table1 = new JTable(Values,ColumnNames);
        table1.setBounds(100,100,800,400);
        table1.setFont(new Font("Arial",Font.PLAIN,16));




        JScrollPane pane1 = new JScrollPane(table1);
        pane1.setBounds(150,180,800,400);

        panel2.add(pane1);
        panel2.add(label1);
        panel2.add(id1);
        panel2.add(name1);
        panel2.add(update);
        mainFetchPanel.add(panel1);
        mainFetchPanel.add(panel2);
    }
}
