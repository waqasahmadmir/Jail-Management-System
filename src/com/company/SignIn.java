package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class SignIn extends JFrame implements ActionListener,Serializable {
    Connection con;
    JButton signin=new JButton("Sign-in");
    JButton Home=new JButton("Back");
    JButton register=new JButton("Register");
    JButton forget=new JButton("forgot Password");
    JButton request=new JButton("Send Visit Request");
    JTextField textfield = new JTextField();
    JTextField textField2 = new JTextField();
    Container c;
    private JOptionPane OptionPane;

    SignIn(){

        JFrame frame=new JFrame();
        this.con=con;

        this.setTitle("Prison Management System");
        this.setBounds(200,20,750,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setResizable(false);

        c=this.getContentPane();
        c.setLayout(null);
        Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 12);
        register.setFont(myFont);

        ImageIcon icon = new ImageIcon("src/prison management.jpg");
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

        JLabel label1 = new JLabel("Email");
        JLabel label2 = new JLabel("Password");

        textfield.setBounds(280, 300, 170, 30);
        label1.setBounds(200, 300, 80, 30);

        textField2.setBounds(280, 340, 170, 30);
        label2.setBounds(200, 340, 80, 30);

        signin.setBounds(300, 400, 80, 20);

        register.setBounds(590,250,130,20);
        forget.setBounds(400, 400, 125, 20);
        Home.setBounds(590, 500, 80, 20);
        request.setBounds(590,300,130,20);

        Font myFont1 = new Font("Serif", Font.ITALIC | Font.BOLD, 12);
        forget.setFont(myFont1);

        Cursor curse1 = new Cursor(Cursor.HAND_CURSOR);
        signin.setCursor(curse1);
        signin.addActionListener(this::actionPerformed);

        forget.setCursor(curse1);
        forget.addActionListener(this::actionPerformed);

        register.setCursor(curse1);
        register.addActionListener(this::actionPerformed);

        Font myFont2 = new Font("Serif", Font.ITALIC | Font.BOLD, 12);
        request.setFont(myFont2);
        request.setCursor(curse1);
        request.addActionListener(this::actionPerformed);
        c.add(request);

        Home.setCursor(curse1);
        Home.addActionListener(this::actionPerformed);
        c.add(Home);

        c.add(textField2);
        c.add(textfield);

        c.add(label);
        c.add(label1);
        c.add(label2);

        c.add(register);
        c.add(signin);
        c.add(forget);
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

            if (e.getSource() == register) {
                dispose();
                SignupClass signupClass = new SignupClass();

            }
            else if (e.getSource() == signin) {

                String s = textfield.getText();
                String s2 = textField2.getText();
                if(textfield.getText().isEmpty() || textField2.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Fields cannot be empty");
                }
                else{
                    String sql = "select * from login where user_name=? and password=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, s);
                    pst.setString(2,s2);
                    ResultSet rs = pst.executeQuery();
                    String user=null;
                    String pass=null;
                    while (rs.next()){
                        user=rs.getString(1);
                        pass=rs.getString(2);
                    }

                    try {
                        if (user.equals(s) && pass.equals(s2)) {

                            dispose();
                            Front front = new Front();
                        }
                    }
                    catch (Exception error){
                        JOptionPane.showMessageDialog(null,"Invalid Email or Password");
                    }

                }
            }
            else if (e.getSource() == Home) {
                dispose();
            }
            else if (e.getSource() == forget) {
                dispose();
                Forgetpassword forgetpassword=new Forgetpassword();


            }
            else if (e.getSource() == request) {
                dispose();
                new VisitRequest();

            }
        }
        catch (Exception ee) {
            JOptionPane.showMessageDialog(new JFrame(),"Connection Failed");
        }
    }
}
