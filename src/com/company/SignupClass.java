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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupClass extends JFrame implements ActionListener, Serializable {
    JTextField textfield1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JTextField textField4 = new JTextField();
    JTextField textField5 = new JTextField();
    JTextField textField6 = new JTextField();
    JTextField textField7 = new JTextField();
    JTextField textField8 = new JTextField();
    JTextField textField9 = new JTextField();
    JTextField textField10 = new JTextField();

    JButton signin=new JButton("Register");
    JButton Home=new JButton("Back");
    Container c;
    SignupClass(){


        JFrame frame=new JFrame();
        this.setTitle("Prison Management System");
        this.setBounds(200,20,750,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        c=this.getContentPane();
        c.setLayout(null);

        ImageIcon icon = new ImageIcon("src/prison management.jpg");
        JLabel label = new JLabel(icon);

        JLabel label1 = new JLabel("Jailer ID");
        JLabel label2 = new JLabel("First name");
        JLabel label3=new JLabel("Last name");
        JLabel label4=new JLabel("Age");
        JLabel label5=new JLabel("Address");
        JLabel label6=new JLabel("Email");
        JLabel label7=new JLabel("Password");
        JLabel label8=new JLabel("Phone Number");
        JLabel label9=new JLabel("Network Type");
        JLabel label10=new JLabel("Contact Status");
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

        textfield1.setBounds(180, 250, 155, 30);
        label1.setBounds(110, 250, 130, 30);

        textField2.setBounds(180, 290, 155, 30);
        label2.setBounds(110, 290, 80, 30);

        textField3.setBounds(180, 330, 155, 30);
        label3.setBounds(110, 330, 80, 30);


        textField6.setBounds(180, 370, 155, 30);
        label6.setBounds(110, 370, 80, 30);

        textField7.setBounds(180, 410, 155, 30);
        label7.setBounds(110,410,80,30);

        textField4.setBounds(510, 250, 140, 30);
        label4.setBounds(420, 250, 130, 30);

        textField5.setBounds(510, 290, 140, 30);
        label5.setBounds(420, 290, 130, 30);

        textField8.setBounds(510, 330, 140, 30);
        label8.setBounds(420,330,100,30);

        textField9.setBounds(510, 370, 140, 30);
        label9.setBounds(420,370,100,30);

        textField10.setBounds(510, 410, 140, 30);
        label10.setBounds(420,410,100,30);

        signin.setBounds(270, 475, 100, 20);
        Cursor curse1 = new Cursor(Cursor.HAND_CURSOR);
        signin.setCursor(curse1);
        signin.addActionListener(this::actionPerformed);
        Home.setBounds(380, 475, 100, 20);
        Home.addActionListener(this::actionPerformed);

        c.add(Home);
        c.add(textField3);
        c.add(textField2);
        c.add(textfield1);
        c.add(label1);
        c.add(label2);
        c.add(label3);
        c.add(label4);
        c.add(label5);
        c.add(label6);
        c.add(label7);
        c.add(label8);
        c.add(label9);
        c.add(label10);
        c.add(textField4);
        c.add(textField5);
        c.add(textField6);
        c.add(textField7);
        c.add(textField8);
        c.add(textField9);
        c.add(textField10);
        c.setBackground(Color.gray);
        c.add(label);
        c.add(signin);

        JLabel labell=new JLabel("Developed By Waqas Ahmad");
        Font myFontt = new Font("Serif", Font.ITALIC | Font.BOLD, 12);
        labell.setFont(myFontt);
        labell.setBounds(300,530,200, 25);
        labell.setBackground(Color.GREEN);
        c.add(labell);
        this.setVisible(true);


    }
    @Override

    public void actionPerformed(ActionEvent e) {
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DBMS", "12345");

            if (e.getSource()==Home){
                dispose();
                new SignIn();
            }
            else if (e.getSource() == signin) {
                if (textfield1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty() || textField4.getText().isEmpty()
                        || textField5.getText().isEmpty() || textField6.getText().isEmpty() || textField7.getText().isEmpty() || textField8.getText().isEmpty()
                        || textField9.getText().isEmpty() || textField10.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field cannot be empty");
                }
                else {
                    String s1 = textfield1.getText();
                    String s2 = textField2.getText();
                    String s3 = textField3.getText();
                    String s4 = textField4.getText();
                    String s5 = textField5.getText();
                    String s6 = textField6.getText();
                    String s7 = textField7.getText();
                    String s8 = textField8.getText();
                    String s9 = textField9.getText();
                    String s10 = textField10.getText();

                    String sqlt = "select COUNT(*) AS rowcount from jailor where jailor_id=?";
                    PreparedStatement psttt = con.prepareStatement(sqlt);
                    psttt.setString(1, s1);
                    ResultSet rs = psttt.executeQuery();
                    rs.next();
                    int count=rs.getInt("rowcount");
                    rs.close();
                    System.out.println(" jailer rows :"+count);
                    if(count>0){
                        JOptionPane.showMessageDialog(null, "Jailer with this ID Already exist\nTry Any other ID");
                    }
                    else{
                        String sql1 = "select COUNT(*) AS rowcount from login where USER_NAME=?";
                        PreparedStatement pst1 = con.prepareStatement(sql1);
                        pst1.setString(1, s6);
                        ResultSet rs1 = pst1.executeQuery();
                        rs1.next();
                        int count1=rs1.getInt("rowcount");
                        rs1.close();
                        if(count1==0){
                            final String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                            Pattern pattern = Pattern.compile(regex);
                            Matcher matcher = pattern.matcher(s6);
                            boolean match=(matcher.matches() ? true: false);

                            if(match){
                                if(s7.length()<4 || s7.length()>12){
                                    JOptionPane.showMessageDialog(null,"Password cannot be length less then 4 and greater than 12");

                                }
                                else{

                                    String sql5 = "select COUNT(*) AS rowcount from PHONE where \"number\"=?";
                                    PreparedStatement pst5 = con.prepareStatement(sql5);
                                    pst5.setString(1, s8);

                                    ResultSet rs5 = pst5.executeQuery();
                                    rs5.next();

                                    int count5=rs5.getInt("rowcount");
                                    rs5.close();
                                    if(count5==0){
                                        if(s8.length()!=11){
                                            JOptionPane.showMessageDialog(null,"Contact number must be of length 11");

                                        }

                                        else {

                                            if(s8.chars().allMatch( Character::isDigit )){
                                                String sql2 = "insert into login values (?,?)";
                                                PreparedStatement pst2 = con.prepareStatement(sql2);
                                                pst2.setString(1, s6);
                                                pst2.setString(2, s7);
                                                ResultSet rs2 = pst2.executeQuery();
                                                String sql = "insert into Jailor values (?,?,?,?,?,?)";
                                                PreparedStatement pst = con.prepareStatement(sql);
                                                pst.setString(1, s1);
                                                pst.setString(2, s2);
                                                pst.setString(3, s3);
                                                pst.setString(4, s4);
                                                pst.setString(5, s5);
                                                pst.setString(6, s6);
                                                ResultSet rss = pst.executeQuery();


                                                String sql3 = "insert into Phone values (?,?,?,?)";
                                                PreparedStatement pst3 = con.prepareStatement(sql3);
                                                pst3.setString(1, s8);
                                                pst3.setString(2, s9);
                                                pst3.setString(3, s10);

                                                pst3.setString(4, s1);

                                                ResultSet rs3 = pst3.executeQuery();

                                                JOptionPane.showMessageDialog(null, "Jailer Register Successfully");

                                                dispose();
                                                SignIn signIn = new SignIn();
                                            }
                                            else {
                                                JOptionPane.showMessageDialog(null, "Contact number can only be digit");

                                            }

                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Jailer with this Phone Number Already exist!!");

                                    }

                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Invalid Email Address");

                            }

                        }
                        else{

                            JOptionPane.showMessageDialog(null, "Jailer with email "+s6+"Already exist");
                        }
                    }
                }
            }
        }
        catch(Exception ee){
                JOptionPane.showMessageDialog(null, ee.toString());
            System.out.println(ee.toString());

            }
        }
    }
