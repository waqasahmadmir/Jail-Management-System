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

public class Case extends JFrame implements ActionListener, Serializable {
    JTextField textfield1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JTextField textField4 = new JTextField();

    JButton confirm=new JButton("Confirm");
    JButton Home=new JButton("Back");
    Container c;
    Case(){
        JFrame frame=new JFrame();
        this.setTitle("Prison Management System");
        this.setBounds(200,20,750,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        c=this.getContentPane();
        c.setLayout(null);

        ImageIcon icon = new ImageIcon("src/prison management.jpg");
        JLabel label = new JLabel(icon);

        JLabel label1 = new JLabel("Case ID");
        JLabel label2 = new JLabel("Case Type");
        JLabel label3=new JLabel("Case Year");
        JLabel label4=new JLabel("Prisoner ID");
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

        textfield1.setBounds(200, 270, 120, 30);
        label1.setBounds(110, 270, 110, 30);

        textField2.setBounds(200, 330, 120, 30);
        label2.setBounds(110, 330, 110, 30);

        textField3.setBounds(510, 270, 120, 30);
        label3.setBounds(420, 270, 110, 30);

        textField4.setBounds(510, 330, 120, 30);
        label4.setBounds(420, 330, 110, 30);

        confirm.setBounds(270, 420, 100, 20);
        Cursor curse1 = new Cursor(Cursor.HAND_CURSOR);
        confirm.setCursor(curse1);
        confirm.addActionListener(this::actionPerformed);
        Home.setBounds(380, 420, 100, 20);
        Home.addActionListener(this::actionPerformed);
        Home.setCursor(curse1);

        c.add(textfield1);
        c.add(textField2);
        c.add(textField3);
        c.add(textField4);
        c.add(label1);
        c.add(label2);
        c.add(label3);
        c.add(label4);

        c.setBackground(Color.gray);
        c.add(label);

        c.add(Home);
        c.add(confirm);

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
        try{
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DBMS", "12345");

            if (e.getSource() == confirm) {
                if (textfield1.getText().isEmpty() || textField2.getText().isEmpty() ||
                        textField3.getText().isEmpty() || textField4.getText().isEmpty()
                ) {
                    JOptionPane.showMessageDialog(null, "Field cannot be empty");
                }
                else {
                    String s1 = textfield1.getText();
                    String s2 = textField2.getText();
                    String s3 = textField3.getText();
                    String s4 = textField4.getText();

                    String sql = "select COUNT(*) AS rowcount from CASE where CASE_ID=?";

                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, s1);
                    System.out.println("cell no "+s1);
                    ResultSet rs = pst.executeQuery();
                    rs.next();
                    int count=rs.getInt("rowcount");
                    rs.close();
                    if(count!=0){
                        JOptionPane.showMessageDialog(null, "Case with ID "+s1+"Already exist\nTry Any other!!!");
                    }
                    else{
                        String sql2 = "select COUNT(*) AS rowcount from Prisoners where Prisoner_id=?";
                        PreparedStatement pst2 = con.prepareStatement(sql2);
                        pst2.setString(1, s4);
                        ResultSet rs2 = pst2.executeQuery();
                        rs2.next();
                        int count2=rs2.getInt("rowcount");
                        rs2.close();
                        if (count2>0){
                            String sql3="insert into Case values (?,?,?,?)";
                            PreparedStatement pst3=con.prepareStatement(sql3);
                            pst3.setString(1,s1);
                            pst3.setString(2,s2);
                            pst3.setString(3,s3);
                            pst3.setString(4,s4);
                            pst3.executeQuery();
                            JOptionPane.showMessageDialog(null, "Case Added Successfully");
                            dispose();
                            new Prisoner();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Prisoner with ID "+s1+" Not exist!!!");
                        }
                    }
                }
            }
            else if (e.getSource()==Home){
                dispose();
                new Prisoner();
            }
        }
        catch(Exception ee){
            JOptionPane.showMessageDialog(null, ee.toString());
        }
    }
}
