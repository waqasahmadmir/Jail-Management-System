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

public class PrisonUpdateFrame extends JFrame implements ActionListener, Serializable {
    private String id;
    private String datein;
    JTextField textfield1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JTextField textField4 = new JTextField();
    JTextField textField5 = new JTextField();
    JTextField textField6 = new JTextField();


    JComboBox box1=new JComboBox();

    JButton confirm=new JButton("Update");
    JButton Home=new JButton("Back");
    Container c;
    PrisonUpdateFrame(String id,String datein){
        this.id=id;
        this.datein=datein;

        JFrame frame=new JFrame();
        this.setTitle("Prison Management System");
        this.setBounds(200,20,750,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        c=this.getContentPane();
        c.setLayout(null);

        ImageIcon icon = new ImageIcon("src/prison management.jpg");
        JLabel label = new JLabel(icon);

        JLabel label1 = new JLabel("Prison ID");
        JLabel label2 = new JLabel("First Name");
        JLabel label3=new JLabel("Last Name");
        JLabel label4=new JLabel("Age");
        JLabel label5=new JLabel("Date of In");
        JLabel label6=new JLabel("Date of Out");
        JLabel label7=new JLabel("Cell Number");

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

        int value=1;
        int value2=2;
        int value3=3;
        box1.addItem(value);
        box1.addItem(value2);
        box1.addItem(value3);

        box1.setBounds(510, 330, 155, 30);
        label7.setBounds(410, 330, 130, 30);


        confirm.setBounds(270, 475, 100, 20);
        Cursor curse1 = new Cursor(Cursor.HAND_CURSOR);
        confirm.setCursor(curse1);
        confirm.addActionListener(this::actionPerformed);
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
        c.add(textField4);
        c.add(textField5);
        c.add(textField6);
        c.add(box1);
        c.setBackground(Color.gray);
        c.add(label);
        c.add(confirm);


        textfield1.setText(id);
        textfield1.setEditable(false);

        textField5.setText(datein);
        textField5.setEditable(false);

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

            if (e.getSource() == confirm) {
                if (textField2.getText().isEmpty() ||textField3.getText().isEmpty() || textField4.getText().isEmpty()
                        || textField6.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field cannot be empty");
                }
                else {
                    String s2 =textField2.getText();
                    String s3=textField3.getText();
                    String s4=textField4.getText();
                    String s6=textField6.getText();
                    String s7=box1.getSelectedItem().toString();

                    String sql="update PRISONERS set FIRST_NAME=?,LAST_NAME=?,AGE=?,DATE_OF_OUT=?,CELL_CELL_NO=? where PRISONER_ID=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, s2);
                    pst.setString(2, s3);
                    pst.setString(3, s4);
                    pst.setDate(4, java.sql.Date.valueOf(s6));
                    pst.setString(5, s7);
                    pst.setString(6,id);
                    ResultSet rs = pst.executeQuery();
                    JOptionPane.showMessageDialog(null,"Record Updated Successfully");

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
