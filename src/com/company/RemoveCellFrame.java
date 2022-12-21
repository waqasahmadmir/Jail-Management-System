package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.*;
import java.util.Scanner;

public class RemoveCellFrame extends JFrame implements ActionListener,Serializable {


    JButton Home=new JButton("Back");
    JButton delete=new JButton("Remove");

    JTextField textField = new JTextField();

    private JOptionPane OptionPane;
    Container c;
    RemoveCellFrame(){

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

        JLabel label1 = new JLabel("Enter Cell No. ");


        textField.setBounds(335, 300, 160, 30);
        label1.setBounds(240, 300, 130, 30);

        Home.setBounds(450, 400, 80, 20);
        delete.setBounds(335, 400, 80, 20);

        Font myFont1 = new Font("Serif", Font.ITALIC | Font.BOLD, 12);

        Cursor curse1 = new Cursor(Cursor.HAND_CURSOR);

        Home.setCursor(curse1);
        Home.addActionListener(this::actionPerformed);
        c.add(Home);

        delete.setCursor(curse1);
        delete.addActionListener(this::actionPerformed);
        c.add(delete);


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
                new MainMenu();

            }
            else if (e.getSource() == delete) {
                String sql = "select COUNT(*) AS rowcount from CELL where CELL_NO=?";

                String s1=textField.getText();
                PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, s1);
                ResultSet res = pst.executeQuery();
                res.next();
                int count=res.getInt("rowcount");
                res.close();
                if(count!=0){
                    String sql1 = "Delete from CELL where CELL_NO=?";
                    PreparedStatement pst1= con.prepareStatement(sql1);
                    pst1.setString(1, s1);
                    ResultSet res1 = pst1.executeQuery();
                    JOptionPane.showMessageDialog(null,"Cell Removed Successfully");
                }
                else {

                    JOptionPane.showMessageDialog(null,"Cell NO. "+s1+" Does not Exist");
                }
            }

        }
        catch (Exception ee) {
            System.out.println(ee.toString());
            JOptionPane.showMessageDialog(new JFrame(),ee.toString());
        }
    }
}
