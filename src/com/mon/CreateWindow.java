package com.mon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateWindow {
    JFrame frame;
    JPanel cross;
    JLabel crossed;
    JPanel panel,mainpanel;

    int x_axis,y_axis;

    CreateWindow(int x,int y,int width, int height){
        CustomModelMon ob1 = new CustomModelMon();

        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setLayout(null);
        frame.setBounds(x, y, width, height);

        mainpanel = new JPanel();
        mainpanel.setBounds(0,0,width,height);
        mainpanel.setBackground(ob1.darkBack);
        mainpanel.setLayout(null);



        panel = new JPanel();
        panel.setBounds(0,0,width,30);
        panel.setLayout(null);
        panel.setBackground(ob1.darkBack);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x_axis = e.getX();
                y_axis = e.getY();
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xx = e.getXOnScreen();
                int yy = e.getYOnScreen();
                frame.setLocation(xx-x_axis,yy-y_axis);
            }
        });

//        panel.setBorder(BorderFactory.createMatteBorder(1,0,0,0,new Color(220,20,60)));

        CustomModelMon ob = new CustomModelMon();

        cross = new JPanel();
        cross.setBounds(width-50,0,50,30);
        cross.setBackground(ob.darkBack);

        crossed = new JLabel("X",SwingConstants.CENTER);
        crossed.setForeground(Color.WHITE);

        cross.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
            }
        });
        cross.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cross.add(crossed);
        panel.add(cross);

        mainpanel.setBorder(BorderFactory.createMatteBorder(0,1,2,1,new Color(220,20,60)));
        panel.setBorder(BorderFactory.createMatteBorder(1,1,0,1,new Color(220,20,60)));
        cross.setBorder(BorderFactory.createMatteBorder(1,0,0,1,new Color(220,20,60)));

        mainpanel.add(panel);

        UsedImages obimg = new UsedImages();
        ImageIcon img = new ImageIcon(obimg.mainIcon);
        frame.setIconImage(img.getImage());
        frame.add(mainpanel);
    }



}
