package com.mon;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;


public class CustomModelMon {

    JComboBox day,month,year;
    Font customFont,customFont2,customFont3,customFont4;
    Color darkBack = new Color(28,32,57);
    Color darkBackPanel = new Color(37,44,72);
    Color hoverColors = new Color(37,44,85);
    Color DashBoradBgColor = new Color(19,26,43);
    Color DashBoradCardColor = new Color(31,41,64);
    int x_axis, y_axis;
    JProgressBar bar;
    JPanel panel,crossPanel,datePanel,hpanel;
    JPanel hoverPanel;
    JPanel customButton;
    JPanel titlePanel;
    JLabel label,image_label;
    JButton button;
    JTextField textField;
    ImageIcon image,resized_image;
    JComboBox box;
    JTable table;
    JScrollPane scrollPane;


    CustomModelMon(){
        try {
            String dir = System.getProperty("user.dir");
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(dir+"\\src\\fonts\\CAMPUS PERSONAL USE.ttf")).deriveFont(16f);
            customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File(dir+"\\src\\fonts\\Sunday Best ttf.ttf")).deriveFont(10f);
            customFont3 = Font.createFont(Font.TRUETYPE_FONT, new File(dir+"\\src\\fonts\\YanoneKaffeesatz-Bold.ttf")).deriveFont(24f);
            customFont4 = Font.createFont(Font.TRUETYPE_FONT, new File(dir+"\\src\\fonts\\Bleeding_Cowboys.ttf")).deriveFont(24f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            ge.registerFont(customFont2);
            ge.registerFont(customFont3);
            ge.registerFont(customFont4);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void CreateDatePicker(int x,int y,int width,int height){
        String[] months ={"Month","January","February","March","April","May","June","July","August","September","October","November","December"};
        String[] days = new String[32];
        days[0]="Day";
        for(int i=1;i<=31;i++){
            days[i]=String.valueOf(i);
        }

        String[] years = new String[100];
        years[0]="Year";
        int count=0;
        for(int j=1950;j<=2022;j++){
            years[count++]=String.valueOf(j);
        }

        day = new JComboBox(days);
        month = new JComboBox(months);
        year = new JComboBox(years);

        day.setBounds(0,0,50,height);
        month.setBounds(70,0,90,height);
        year.setBounds(180,0,70,height);

        day.setBackground(new Color(163,184,204));
        month.setBackground(new Color(163,184,204));
        year.setBackground(new Color(163,184,204));

//        day.setForeground(Color.WHITE);
//        month.setForeground(Color.WHITE);
//        year.setForeground(Color.WHITE);


        datePanel = new JPanel();
        datePanel.setLayout(null);
        datePanel.setBounds(x,y,width,height);
        datePanel.add(day);
        datePanel.add(month);
        datePanel.add(year);


    }

    public void CreatePanel(int x,int y,int width,int height){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x,y,width,height);
    }

    public void CreateHoverButton(int x,int y,int width,int height,String btn_name,String paths){
        hpanel = new JPanel();
        hpanel.setLayout(null);
        hpanel.setBounds(x,y,width,height);
        hpanel.setBackground(DashBoradCardColor);

        ImageIcon btn_image = new ImageIcon(paths);
        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(btn_image);
        imgLabel.setBounds((width/2)-40,20,80,80);

        JPanel textPanel = new JPanel();
        textPanel.setBounds(1,110,width-2,29);
        textPanel.setBackground(DashBoradCardColor);

        JLabel buttonLabel = new JLabel(btn_name,SwingConstants.CENTER);
        buttonLabel.setForeground(Color.white);
        textPanel.add(buttonLabel);
        hpanel.add(textPanel);
        hpanel.add(imgLabel);
        hpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hpanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.gray));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                hpanel.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.gray));
            }
        });
        hpanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }
    public void CreateRadiusPanel(int x,int y,int width,int height){
            panel = new JPanel();
            panel.setLayout(null);
            panel.setBounds(x,y,width,height);
            panel.setBackground(DashBoradCardColor);
//            panel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,new Color(220,20,60)));



        }

    public void CreateCrossButton(int x,int y,int width,int height,JFrame frame){
        crossPanel = new JPanel();
        crossPanel.setBackground(darkBack);
        crossPanel.setBounds(x,y,width,height);
        JLabel lb = new JLabel("X",SwingConstants.CENTER);
        lb.setForeground(Color.WHITE);
        crossPanel.add(lb);
        crossPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    public void CreateCustomButton(int x,int y,int width,int height,String btn_name,String path){

        customButton = new JPanel();
        customButton.setBounds(x,y,width,height);
        customButton.setLayout(null);
        customButton.setBackground(darkBackPanel);



        ImageIcon im_icon = new ImageIcon(path);
        JLabel img = new JLabel();
        img.setIcon(im_icon);
        img.setBounds((width/2)-35,20,70,70);

        JPanel textPanel = new JPanel();
        textPanel.setBounds(0,100,width,50);
        textPanel.setBackground(darkBackPanel);


        customButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                customButton.setBackground(hoverColors);
                textPanel.setBackground(hoverColors);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                customButton.setBackground(darkBackPanel);
                textPanel.setBackground(darkBackPanel);
            }
        });

        JLabel b_name = new JLabel(btn_name,SwingConstants.CENTER);



        b_name.setFont(customFont);
        b_name.setForeground(Color.WHITE);

        customButton.add(img);
        textPanel.add(b_name);
        customButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        customButton.add(textPanel);


    }


    public void CreateTitlePanel(int x,int y,int width,int height,JFrame frame){

        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setBounds(x,y,width,height);
        titlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x_axis=e.getX();
                y_axis=e.getY();
            }
        });

        titlePanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xx = e.getXOnScreen();
                int yy = e.getYOnScreen();
                frame.setLocation(xx-x_axis,yy-y_axis);
            }
        });


    }

    public void CreateHoverPanel(int x,int y,int width,int height,Color hovercolor,Color bgColor){
        hoverPanel = new JPanel();
        hoverPanel.setLayout(null);
        hoverPanel.setBounds(x,y,width,height);
        hoverPanel.setBackground(bgColor);
        hoverPanel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                hoverPanel.setBackground(hovercolor);
            }

            @Override
            public void mouseExited(MouseEvent e){
                hoverPanel.setBackground(bgColor);
            }
        });
    }

    public void CreateLabel(int x,int y,int wid,int hei,String text,Font fn_name){
        label = new JLabel();
        label.setText(text);
        label.setBounds(x,y,wid,hei);
        label.setFont(fn_name);
    }

    public void CreateButton(int x,int y,int wid,int hei,String btn_name){
        button = new JButton(btn_name);
        button.setFocusable(false);
        button.setBounds(x,y,wid,hei);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(220,20,60));
        button.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.WHITE));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(165,42,42));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(220,20,60));
            }
        });
    }


    public void CreateButtonColors(int x,int y,int wid,int hei,String btn_name,Color color){
        button = new JButton(btn_name);
        button.setFocusable(false);
        button.setBounds(x,y,wid,hei);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.WHITE));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }



    public void CreateTextfieldUpdate(int x,int y,int wid,int hei,String placeholder){
        textField = new JTextField(placeholder);
        textField.setBounds(x,y,wid,hei);
        textField.setBorder(BorderFactory.createMatteBorder(3,15,3,3,darkBackPanel));

    }

    public void CreateTextfield(int x,int y,int wid,int hei,String placeholder){
        textField = new JTextField(placeholder);
        textField.setBounds(x,y,wid,hei);
        textField.setBorder(BorderFactory.createMatteBorder(3,15,3,3,darkBackPanel));
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textField.getText().equals(placeholder)){
                    textField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(textField.getText().equals("")){
                    textField.setText(placeholder);
                }
            }


        });
    }

    public void CreateTextfieldFirst(int x,int y,int wid,int hei,String placeholder){
        textField = new JTextField(placeholder);
        textField.setBounds(x,y,wid,hei);
        textField.setBorder(BorderFactory.createMatteBorder(3,15,3,3,darkBackPanel));
        textField.setText(placeholder+"!");

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textField.getText().equals(placeholder)){
                    textField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(textField.getText().equals("")){
                    textField.setText(placeholder);
                }
            }


        });
    }

    public void CreateCombo(int x,int y,int wid,int hei,String[] options){
        box = new JComboBox(options);
        box.setBounds(x,y,wid,hei);
    }

    public void CreateImage(int x,int y,int wid,int hei,String path){
        image = new ImageIcon(path);
        image_label = new JLabel();
        image_label.setIcon(image);
        image_label.setBounds(x,y,wid,hei);
    }

    public void CreateResizeImage(int x,int y,int wid,int hei,String path){
        resized_image = new ImageIcon(path);
        Image rez = resized_image.getImage().getScaledInstance(wid,hei,Image.SCALE_DEFAULT);
        ImageIcon rex = new ImageIcon(rez);
        image_label = new JLabel();
        image_label.setIcon(rex);
        image_label.setBounds(x,y,wid,hei);
    }

    public void CreateProgressBar(int x,int y,int width,int height){
        bar = new JProgressBar();
        bar.setValue(0);
        bar.setBounds(x,y,width,height);
        bar.setBackground(darkBack);
        bar.setForeground(Color.white);
        bar.setBorder(BorderFactory.createMatteBorder(2,0,0,0,darkBack));
//        bar.setStringPainted(true);
    }
    public void fill(JFrame frame){
        int counter = 0;

        while(counter<=100){
            bar.setValue(counter);
            try{
                Thread.sleep(50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            counter+=1;
            if(counter==100){
                frame.setVisible(false);
                HomeScreen ob = new HomeScreen();
                ob.CreateScreen();
            }
        }
    }


    public void CreateTable(int x, int y, int wid, int hei, ResultSet res){

        table = new JTable();
        table.setRowHeight(30);
        table.setForeground(Color.WHITE);
        table.setBackground(DashBoradCardColor);
        table.setModel(DbUtils.resultSetToTableModel(res));
        table.setBounds(0,0,wid,hei);




        JTableHeader th = table.getTableHeader();
        th.setBackground(DashBoradCardColor);
        th.setForeground(Color.WHITE);
        th.setPreferredSize(new Dimension(100, 40));
        th.setFont(new Font("Segoe UI",Font.BOLD,12));


        //To Center All Column Value
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);


        TableModel tableModel = table.getModel();
        for(int c=0;c<tableModel.getColumnCount();c++) {
            table.getColumnModel().getColumn(c).setCellRenderer(cellRenderer);
        }

        JTableHeader hed = table.getTableHeader();
//        hed.setBorder(BorderFactory.createMatteBorder(1,1,1,1,DashBoradCardColor));

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(x,y,wid,hei);
        scrollPane.getViewport().setBackground(DashBoradCardColor);
        scrollPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,DashBoradCardColor));
    }


    public int SendData(String command){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/smartie", "root","");
            Statement st = con.createStatement();
            st.execute(command);
            return 1;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ResultSet GetData(String Command){
        ResultSet rz=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/smartie", "root","");
            Statement st = con.createStatement();
            rz = st.executeQuery(Command);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return rz;
    }


}
