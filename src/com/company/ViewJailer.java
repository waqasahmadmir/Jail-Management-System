package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.*;
import java.util.Scanner;

public class ViewJailer extends JFrame implements ActionListener,Serializable {


    JButton Home=new JButton("Back");
    JButton view=new JButton("View");

    JTextField textField = new JTextField();

    private JOptionPane OptionPane;
    Container c;
    ViewJailer(){

        JFrame frame=new JFrame();

        this.setTitle("Prison Management System");
        this.setBounds(200,20,750,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setResizable(false);

        c=this.getContentPane();
        c.setLayout(null);
        Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 12);

        ImageIcon icon = new ImageIcon("src/prison management.jpg");
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

        JLabel label1 = new JLabel("Enter Your ID");


        textField.setBounds(335, 300, 160, 30);
        label1.setBounds(240, 300, 130, 30);

        Home.setBounds(450, 400, 80, 20);
        view.setBounds(335, 400, 80, 20);

        Font myFont1 = new Font("Serif", Font.ITALIC | Font.BOLD, 12);

        Cursor curse1 = new Cursor(Cursor.HAND_CURSOR);

        Home.setCursor(curse1);
        Home.addActionListener(this::actionPerformed);
        c.add(Home);

        view.setCursor(curse1);
        view.addActionListener(this::actionPerformed);
        c.add(view);


        c.add(textField);
        c.add(label);
        c.add(label1);

        c.setBackground(Color.gray);

        JLabel labell=new JLabel("Developed By Waqas Ahmad");
        Font myFontt = new Font("Serif", Font.ITALIC | Font.BOLD, 12);
        labell.setFont(myFontt);
        labell.setBounds(300,530,200, 25);
        labell.setBackground(Color.GREEN);
        c.add(labell);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DBMS", "12345");

            if (e.getSource() == Home) {
                dispose();
                new MainMenu ();

            }
            else if (e.getSource() == view) {
                String sql = "select COUNT(*) AS rowcount from JAILOR where JAILOR_ID=?";

                String sql1 = "select* from JAILOR where JAILOR_ID=?";

                String s1=textField.getText();
                PreparedStatement pst= con.prepareStatement(sql);

                PreparedStatement pst1= con.prepareStatement(sql1);

                pst.setString(1, s1);
                pst1.setString(1, s1);

                ResultSet res = pst.executeQuery();

                ResultSet res1 = pst1.executeQuery();

                res.next();
                int count=res.getInt("rowcount");
                res.close();

                if(count!=0){

                    String sql2 = "select* from phone where jailor_jailor_id=?";
                    PreparedStatement pst2= con.prepareStatement(sql2);

                    pst2.setString(1, s1);

                    ResultSet res2 = pst2.executeQuery();

                    while (res1.next() && res2.next()){

                        JOptionPane.showMessageDialog(null, "Personal Detail\n"+"Jailer ID: "+res1.getString(1)
                                +"\n"+"First Name: "+res1.getString(2) +"\n"+"Last Name: "+res1.getString(3)
                                +"\n"+"Age: "+res1.getString(4) +"\n"+"Address: "+res1.getString(5)
                                +"\n"+"Email: "+res1.getString(6)+"\nContact Detail"+"\nContact Number :"+res2.getString(1)
                                +"\nNetwork Type :"+res2.getString(2)+"\nCurrent Status:"+res2.getString(3));

                    }
                }
                else {

                    JOptionPane.showMessageDialog(null,"Wrong ID");
                }
            }

        }
        catch (Exception ee) {
            JOptionPane.showMessageDialog(new JFrame(),ee.toString());
        }
    }
}
