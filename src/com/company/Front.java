package com.company;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class Front extends JFrame implements ActionListener {

        JButton prison=new JButton("Prisoner");
        JButton guard=new JButton("Guard");
        JButton visitor=new JButton("Visitor");
        JButton jailer=new JButton("Jailer Menu");
        JButton logout=new JButton("Logout");
        Container c;
        Front(){


                JFrame frame=new JFrame();
                this.setTitle("Prison Management System");
                this.setBounds(200,20,750,600);
                this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                this.setResizable(false);
                c=this.getContentPane();
                c.setLayout(null);

                ImageIcon icon = new ImageIcon("src/frontphoto.jpg");
                JLabel label = new JLabel(icon);
                label.setBounds(10, 120, icon.getIconWidth(), icon.getIconHeight());

                c.add(jailer);
                jailer.setBounds(50, 90, 100, 25);
                jailer.addActionListener(this::actionPerformed);

                prison.setBounds(200, 90, 100, 25);
                prison.addActionListener(this::actionPerformed);
                c.add(prison);

                guard.setBounds(350, 90, 100, 25);
                guard.addActionListener(this::actionPerformed);
                c.add(guard);

                visitor.setBounds(500, 90, 100, 25);
                visitor.addActionListener(this::actionPerformed);
                c.add(visitor);

                logout.setBounds(640,500,80, 20);
                logout.addActionListener(this::actionPerformed);

                c.add(logout);

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
                prison.setCursor(curse1);
                guard.setCursor(curse1);
                visitor.setCursor(curse1);
                c.setBackground(Color.gray);
                c.add(label);
                c.add(label1);
                c.add(label2);
                this.setVisible(true);

        }

        public void actionPerformed(ActionEvent e) {
                try{
                        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DBMS", "12345");
                        if (e.getSource() == prison) {
                               dispose();
                               new Prisoner();
                        }
                        else if(e.getSource() == visitor)
                        {
                                dispose();
                                Visitor visitor=new Visitor();
                        }
                        else if(e.getSource() == guard){
                                dispose();
                                Guard guard=new Guard();
                        }

                        else if(e.getSource() == logout){
                                dispose();
                                new SignIn();
                        }
                        else if(e.getSource() == jailer){
                                dispose();
                                new MainMenu();
                        }

                }
                catch (Exception ee){
                        System.out.println(ee);
                }
        }
        }