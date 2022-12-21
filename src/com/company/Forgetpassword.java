package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Forgetpassword extends JFrame implements ActionListener, Serializable {
    Connection con;
    JButton confirm=new JButton("confirm");
    JButton Home=new JButton("Back");

    JTextField textfield = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    Container c;
    private JOptionPane OptionPane;

    Forgetpassword(){

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

        JLabel label1 = new JLabel("Enter Email");
        JLabel label2 = new JLabel("New Password");
        JLabel label3 = new JLabel("Password Again");

        textfield.setBounds(325, 300, 170, 30);
        label1.setBounds(225, 300, 130, 30);

        textField2.setBounds(325, 340, 170, 30);
        label2.setBounds(225, 340, 100, 30);

        textField3.setBounds(325, 380, 170, 30);
        label3.setBounds(225, 380, 100, 30);
        confirm.setBounds(325, 420, 80, 20);

        Home.setBounds(600, 500, 80, 20);

        Font myFont1 = new Font("Serif", Font.ITALIC | Font.BOLD, 12);

        Cursor curse1 = new Cursor(Cursor.HAND_CURSOR);
        confirm.setCursor(curse1);
        confirm.addActionListener(this::actionPerformed);


        Home.setCursor(curse1);
        Home.addActionListener(this::actionPerformed);
        c.add(Home);

        c.add(textField2);
        c.add(textfield);
        c.add(textField3);

        c.add(label);
        c.add(label1);
        c.add(label2);
        c.add(label3);

        c.add(confirm);

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

            if(e.getSource()==Home){
                dispose();
                new SignIn();
            }
            else if(e.getSource()==confirm){
                String s = textfield.getText();
                String s1 = textField2.getText();
                String s2 = textField3.getText();
                String sql = "select count(*) as rowcount from login where user_name=? ";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, s);
                ResultSet rs = pst.executeQuery();
                rs.next();
                int countt=rs.getInt("rowcount");
                rs.close();
                if(countt>0){
                    if(s1.length()>=4 && s1.length()<13){
                        if(s2.length()>=4 && s2.length()<13){
                            if(s1.equals(s2)){
                                String sql2 = "select count(*) as rowcount from FORGETPASSWORD where PASSWORD=? and AGAIN_PASSWORD=?  and LOGIN_USER_NAME=?";
                                PreparedStatement pst2 = con.prepareStatement(sql2);
                                pst2.setString(1, s1);
                                pst2.setString(2,s2);
                                pst2.setString(3,s);
                                ResultSet rs2 = pst2.executeQuery();
                                rs2.next();
                                int count=rs2.getInt("rowcount");
                                rs2.close();
                                if(count==0){
                                    String sql1 = " UPDATE login SET password =? where user_name=?";
                                    PreparedStatement pst1 = con.prepareStatement(sql1);
                                    pst1.setString(1, s1);
                                    pst1.setString(2,s);
                                    ResultSet rs1 = pst1.executeQuery();
                                    JOptionPane.showMessageDialog(null,"Password Updated");

                                    /*
                                    String sql4="insert into forgetpassword values (?,?,?)";
                                    PreparedStatement pst4= con.prepareStatement(sql4);
                                    pst4.setString(1, s1);
                                    pst4.setString(2,s2);
                                    pst4.setString(3,s);
                                    System.out.println(4);
                                    ResultSet rs4 = pst4.executeQuery();
                                    rs4.next();
                                    System.out.println(5);
                                    rs4.close();*/
                                    SignIn signIn=new SignIn();
                                    dispose();
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"This is one of your previous passwords \nTry any other password");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Please Enter same Password");

                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Password cannot be length less then 4 and greater than 12");

                        }
                    }
                   else{
                        JOptionPane.showMessageDialog(null,"Password cannot be length less then 4 and greater than 12");

                    }

                }
                else{
                    JOptionPane.showMessageDialog(null,"User Does not exist");

                }

                }
        }
        catch (Exception ee) {
            System.out.println(ee.toString());
            JOptionPane.showMessageDialog(null,ee.toString());
        }
    }
}
