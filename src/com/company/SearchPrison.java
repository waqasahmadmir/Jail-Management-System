package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.*;
import java.util.Scanner;

public class SearchPrison extends JFrame implements ActionListener,Serializable {


    JButton Home=new JButton("Back");
    JButton search=new JButton("Search");

    JTextField textField = new JTextField();

    private JOptionPane OptionPane;
    Container c;
    SearchPrison(){

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

        JLabel label1 = new JLabel("Enter Prison ID");


        textField.setBounds(335, 300, 160, 30);
        label1.setBounds(240, 300, 130, 30);

        Home.setBounds(450, 400, 80, 20);
        search.setBounds(335, 400, 80, 20);

        Font myFont1 = new Font("Serif", Font.ITALIC | Font.BOLD, 12);

        Cursor curse1 = new Cursor(Cursor.HAND_CURSOR);

        Home.setCursor(curse1);
        Home.addActionListener(this::actionPerformed);
        c.add(Home);

        search.setCursor(curse1);
        search.addActionListener(this::actionPerformed);
        c.add(search);


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
                new Prisoner ();

            }
            else if (e.getSource() == search) {
                String sql = "select COUNT(*) AS rowcount from Prisoners where PRISONER_ID=?";
                String sql1 = "select* from Prisoners where PRISONER_ID=?";

                String s1=textField.getText();
                PreparedStatement pst= con.prepareStatement(sql);
                PreparedStatement pst1= con.prepareStatement(sql1);

                pst.setString(1, s1);
                pst1.setString(1, s1);

                ResultSet res1 = pst.executeQuery();
                ResultSet res = pst1.executeQuery();

                res1.next();
                int count=res1.getInt("rowcount");
                res1.close();

                if(count!=0){

                    while (res.next()){

                        JOptionPane.showMessageDialog(null, "Prison ID: "+res.getString(1)
                        +"\n"+"First Name: "+res.getString(2) +"\n"+"Last Name: "+res.getString(3)
                                +"\n"+"Age: "+res.getString(4) +"\n"+"Date of In: "+res.getDate(5)
                                +"\n"+"Date of Out: "+res.getDate(6) +"\n"+"Cell No: "+res.getInt(7));

                    }
                }
                else {

                    JOptionPane.showMessageDialog(null,"Prison With ID "+s1+" Does not Exist");
                }
            }

        }
        catch (Exception ee) {
            JOptionPane.showMessageDialog(new JFrame(),ee.toString());
        }
    }
}
