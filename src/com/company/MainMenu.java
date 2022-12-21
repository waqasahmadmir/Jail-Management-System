package com.company;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class MainMenu extends JFrame implements ActionListener {

    JButton own_record=new JButton("View Own Record");
    JButton add_section=new JButton("Add Section");
    JButton add_cell=new JButton("Add Jail Cell");
    JButton remove_cell=new JButton("Remove Cell");
    JButton remove_section=new JButton("Remove Section");
    JButton show_section=new JButton("show Section");
    JButton show_cell=new JButton("Show Cell");

    JButton back=new JButton("Home");
    Container c;
    MainMenu(){


        JFrame frame=new JFrame();
        this.setTitle("Prison Management System");
        this.setBounds(200,20,750,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        c=this.getContentPane();
        c.setLayout(null);

        ImageIcon icon = new ImageIcon("src/frontphoto.jpg");
        JLabel label = new JLabel(icon);
        label.setBounds(10, 120, icon.getIconWidth()-50, icon.getIconHeight());
        own_record.setBounds(590, 130, 135, 25);
        own_record.addActionListener(this::actionPerformed);
        own_record.setBackground(Color.PINK);
        c.add(own_record);

        add_section.setBounds(590, 170, 135, 25);
        add_section.addActionListener(this::actionPerformed);
        add_section.setBackground(Color.PINK);
        c.add(add_section);

        add_cell.setBounds(590, 210, 135, 25);
        add_cell.addActionListener(this::actionPerformed);
        add_cell.setBackground(Color.PINK);
        c.add(add_cell);

        remove_section.setBounds(590, 250, 135, 25);
        remove_section.addActionListener(this::actionPerformed);
        remove_section.setBackground(Color.PINK);
        c.add(remove_section);

        remove_cell.setBounds(590, 290, 135, 25);
        remove_cell.addActionListener(this::actionPerformed);
        remove_cell.setBackground(Color.PINK);
        c.add(remove_cell);

        show_cell.setBounds(590, 330, 135, 25);
        show_cell.addActionListener(this::actionPerformed);
        show_cell.setBackground(Color.PINK);
        c.add(show_cell);

        show_section.setBounds(590, 370, 135, 25);
        show_section.addActionListener(this::actionPerformed);
        show_section.setBackground(Color.PINK);
        c.add(show_section);


        back.setBounds(590, 410, 135, 25);
        back.addActionListener(this::actionPerformed);
        back.setBackground(Color.PINK);
        c.add(back);

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

        own_record.setCursor(curse1);
        add_section.setCursor(curse1);
        add_cell.setCursor(curse1);
        remove_section.setCursor(curse1);
        remove_cell.setCursor(curse1);
        back.setCursor(curse1);
        c.setBackground(Color.gray);
        c.add(label);
        c.add(label1);
        c.add(label2);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DBMS", "12345");
            if (e.getSource() == own_record) {
                dispose();
                new ViewJailer();

            }
            else if(e.getSource() == add_section)
            {
                new Section();
                dispose();

            }
            else if(e.getSource() == add_cell){
                dispose();
                new Cell();
            }

            else if(e.getSource() == remove_section){
                dispose();
                new RemoveSectionFrame();
            }

            else if(e.getSource() == remove_cell){
                dispose();
                new RemoveCellFrame();

            }
            else if(e.getSource() == show_section){
                dispose();
                new ShowSection();

            }
            else if(e.getSource() == show_cell){
                dispose();
               new ShowCell();
            }

            else if(e.getSource() == back){
                dispose();
                new Front();
            }
        }
        catch (Exception ee){
            System.out.println(ee);
        }
    }
}