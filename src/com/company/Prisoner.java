package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Prisoner extends JFrame implements ActionListener {
    JButton add=new JButton("Add Prison");
    JButton search=new JButton("Search Prison");
    JButton view=new JButton("View Prison");
    JButton update=new JButton("Update Record");
    JButton report=new JButton("Generate Report");
    JButton release=new JButton("Release");
    JButton add_case=new JButton("Add Case");
    JButton add_crime=new JButton("Add Crime");
    JButton home= new JButton("Home");
    Container c;
    public Prisoner(){

        JFrame frame=new JFrame();
        this.setTitle("Prison Management System");
        this.setBounds(200,20,750,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        c=this.getContentPane();
        c.setLayout(null);

        ImageIcon icon = new ImageIcon("src/frontphoto.jpg");
        JLabel label = new JLabel(icon);
        label.setBounds(10, 100, icon.getIconWidth()-30, icon.getIconHeight());

        add.setBounds(601, 110, 128, 25);
        add.addActionListener(this::actionPerformed);
        add.setBackground(Color.PINK);
        c.add(add);

        search.setBounds(601, 150, 128, 25);
        search.addActionListener(this::actionPerformed);
        search.setBackground(Color.PINK);
        c.add(search);

        view.setBounds(601, 190, 128, 25);
        view.addActionListener(this::actionPerformed);
        view.setBackground(Color.PINK);
        c.add(view);

        update.setBounds(601, 230, 128, 25);
        update.addActionListener(this::actionPerformed);
        update.setBackground(Color.PINK);
        c.add(update);

        report.setBounds(601, 270, 128, 25);
        report.addActionListener(this::actionPerformed);
        report.setBackground(Color.PINK);
        c.add(report);

        release.setBounds(601, 310, 128, 25);
        release.addActionListener(this::actionPerformed);
        release.setBackground(Color.PINK);
        c.add(release);

        add_case.setBounds(601, 350, 128, 25);
        add_case.addActionListener(this::actionPerformed);
        add_case.setBackground(Color.PINK);
        c.add(add_case);

        add_crime.setBounds(601, 390, 128, 25);
        add_crime.addActionListener(this::actionPerformed);
        add_crime.setBackground(Color.PINK);
        c.add(add_crime);

        home.setBounds(601, 430, 128, 25);
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
        add.setCursor(curse1);
        view.setCursor(curse1);
        update.setCursor(curse1);
        search.setCursor(curse1);
        report.setCursor(curse1);
        release.setCursor(curse1);
        add_case.setCursor(curse1);
        add_crime.setCursor(curse1);
        home.setCursor(curse1);
        c.setBackground(Color.gray);
        c.add(label);
        c.add(label1);
        c.add(label2);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
             dispose();
            new AddPrisonFrame();


        }
        else if(e.getSource() == view)
        {
            dispose();
         new ViewPrisons();
        }
        else if(e.getSource() == update){
            dispose();
            new UpdatePrison();

        }
        else if(e.getSource() == search){
            dispose();
            new SearchPrison();
        }
        else if(e.getSource() == release){
            dispose();
            new ReleasePrison();

        }
        else if(e.getSource() == report){
            dispose();
            new Report();

        }
        else if(e.getSource() == add_case){
            dispose();
            new Case();

        }
        else if(e.getSource() == add_crime){
            dispose();
            new Crime();
        }
        else if(e.getSource() == home){
            dispose();
            new Front();

        }

    }
}
