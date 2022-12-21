package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Visitor extends JFrame implements ActionListener {

    JButton view=new JButton("View Requests");
    JButton reject=new JButton("Reject Requests");
    JButton home=new JButton("Home");
    Container c;
    public Visitor(){

        JFrame frame=new JFrame();
        this.setTitle("Prison Management System");
        this.setBounds(200,20,750,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        c=this.getContentPane();
        c.setLayout(null);

        ImageIcon icon = new ImageIcon("src/frontphoto.jpg");
        JLabel label = new JLabel(icon);
        label.setBounds(10, 100, icon.getIconWidth()-70, icon.getIconHeight());

        view.setBounds(570, 110, 132, 25);
        view.addActionListener(this::actionPerformed);
        view.setBackground(Color.PINK);
        c.add(view);

        reject.setBounds(570, 170, 132, 25);
        reject.addActionListener(this::actionPerformed);
        reject.setBackground(Color.PINK);
        c.add(reject);

        home.setBounds(570, 230, 132, 25);
        home.addActionListener(this::actionPerformed);
        home.setBackground(Color.PINK);
        c.add(home);

        JLabel label1=new JLabel("Prison Management System");
        Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 28);
        label1.setFont(myFont);

        JLabel label2=new JLabel("Developed By Waqas Ahmad");
        Font myFont1 = new Font("Serif", Font.ITALIC | Font.BOLD, 12);
        label2.setFont(myFont1);
        c.add(label2);

        label1.setBounds(200,30,350, 30);
        label1.setBackground(Color.GREEN);
        label2.setBounds(300,530,200, 25);
        label2.setBackground(Color.GREEN);
        Cursor curse1 = new Cursor(Cursor.HAND_CURSOR);
        view.setCursor(curse1);
        reject.setCursor(curse1);
        home.setCursor(curse1);

        c.setBackground(Color.gray);
        c.add(label);
        c.add(label1);
        c.add(label2);
        this.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view) {
            dispose();
             new ViewVisitorRequests();
        }

        else if(e.getSource() == reject){
            dispose();
            new RejectRequestFrame();

        }
        else if(e.getSource() == home){
            dispose();
           new Front();
        }

    }
}
