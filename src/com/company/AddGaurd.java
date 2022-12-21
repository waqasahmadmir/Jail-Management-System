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

public class AddGaurd extends JFrame implements ActionListener, Serializable {
    JTextField textfield1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JTextField textField4 = new JTextField("82203-4576983-7");
    JTextField textField5 = new JTextField();
    JTextField textField6 = new JTextField();
    JTextField textField7 = new JTextField();

    JComboBox box1=new JComboBox();

    JButton confirm=new JButton("Confirm");
    JButton Home=new JButton("Back");
    Container c;
    AddGaurd(){

        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DBMS", "12345");

            JFrame frame = new JFrame();
            this.setTitle("Prison Management System");
            this.setBounds(200, 20, 750, 600);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setResizable(false);
            c = this.getContentPane();
            c.setLayout(null);

            ImageIcon icon = new ImageIcon("src/prison management.jpg");
            JLabel label = new JLabel(icon);

            JLabel label1 = new JLabel("Guard ID");
            JLabel label2 = new JLabel("Guard Name");
            JLabel label3 = new JLabel("Duty Hours");
            JLabel label4 = new JLabel("CNIC");
            JLabel label5 = new JLabel("Salary");
            JLabel label6 = new JLabel("Jailer ID");
            JLabel label7 = new JLabel("Section ID");

            label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

            textfield1.setBounds(180, 250, 155, 30);
            label1.setBounds(100, 250, 130, 30);

            textField2.setBounds(180, 290, 155, 30);
            label2.setBounds(100, 290, 80, 30);

            textField3.setBounds(180, 330, 155, 30);
            label3.setBounds(100, 330, 80, 30);

            textField4.setBounds(180, 370, 155, 30);
            label4.setBounds(100, 370, 130, 30);


            textField5.setBounds(510, 250, 155, 30);
            label5.setBounds(410, 250, 130, 30);

            textField6.setBounds(510, 290, 155, 30);
            label6.setBounds(410, 290, 80, 30);

            String sql = "select * from Section";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int value = rs.getInt(1);
                box1.addItem(value);

            }

            box1.setBounds(510, 330, 155, 30);
            label7.setBounds(410, 330, 130, 30);

            confirm.setBounds(270, 475, 100, 20);
            Cursor curse1 = new Cursor(Cursor.HAND_CURSOR);
            confirm.setCursor(curse1);
            confirm.addActionListener(this::actionPerformed);
            Home.setBounds(380, 475, 100, 20);
            Home.addActionListener(this::actionPerformed);

            c.add(Home);
            c.add(label1);
            c.add(label2);
            c.add(label3);
            c.add(label4);
            c.add(label5);
            c.add(label6);
            c.add(label7);

            c.add(textfield1);
            c.add(textField2);
            c.add(textField3);
            c.add(textField4);
            c.add(textField5);
            c.add(textField6);
            c.add(textField7);

            c.add(box1);
            c.setBackground(Color.gray);
            c.add(label);
            c.add(confirm);

            JLabel labell = new JLabel("Developed By Waqas Ahmad");
            Font myFontt = new Font("Serif", Font.ITALIC | Font.BOLD, 12);
            labell.setFont(myFontt);
            labell.setBounds(300, 530, 200, 25);
            labell.setBackground(Color.GREEN);
            c.add(labell);
            this.setVisible(true);
        }
        catch (Exception ee){
            JOptionPane.showMessageDialog(null, ee.toString());
        }

    }
    @Override

    public void actionPerformed(ActionEvent e) {
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DBMS", "12345");

            if (e.getSource() == confirm) {
                if (textfield1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty() || textField4.getText().isEmpty()
                        || textField4.getText().isEmpty() || textField6.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field cannot be empty");
                }
                else {

                    String s1 = textfield1.getText();
                    String s2 = textField2.getText();
                    String s3 = textField3.getText();
                    String s4 = textField4.getText();
                    Double s5 = Double.parseDouble(textField5.getText());
                    String s6 = textField6.getText();
                    String s7=box1.getSelectedItem().toString();
                    String sql = "select COUNT(*) AS rowcount from GUARDS where GUARD_ID=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, s1);
                    ResultSet rs = pst.executeQuery();
                    rs.next();
                    int count=rs.getInt("rowcount");
                    rs.close();
                    if(count!=0){
                        JOptionPane.showMessageDialog(null, "Guard with this ID Already exist\nTry Any other ID");

                    }

                    else{
                        String sqll = "select COUNT(*) AS rowcount from JAILOR where JAILOR_ID=?";
                        PreparedStatement pstt = con.prepareStatement(sqll);
                        pstt.setString(1, s6);
                        ResultSet rss = pstt.executeQuery();
                        rss.next();
                        int countt=rss.getInt("rowcount");
                        rss.close();

                        if(countt==0){
                            JOptionPane.showMessageDialog(null, "No Jailer with this ID");
                        }
                        else{

                            String sql1 = "insert into GUARDS values (?,?,?,?,?,?,?)";
                            PreparedStatement pst1 = con.prepareStatement(sql1);
                            pst1.setString(1, s1);
                            pst1.setString(2, s2);
                            pst1.setString(3, s3);
                            pst1.setString(4, s4);
                            pst1.setString(5, s6);
                            pst1.setString(6, s7);
                            pst1.setDouble(7, s5);

                            ResultSet resultSet=pst1.executeQuery();
                            JOptionPane.showMessageDialog(null, "Guard Added Successfully");
                            dispose();
                            new Guard();
                    }

                }
                }
            }
            else if (e.getSource()==Home){
                new Guard();
            }
        }
        catch(Exception ee){
            JOptionPane.showMessageDialog(null, ee.toString());

        }
    }
}
