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

public class UpdateGuard extends JFrame implements ActionListener, Serializable {
    private String id;
    private String name;
    private String cnic;
    JTextField textfield1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JTextField textField4 = new JTextField("82203-4576983-7");
    JTextField textField5 = new JTextField();
    JTextField textField6 = new JTextField();
    JTextField textField7 = new JTextField();

    JComboBox box1=new JComboBox();

    JButton confirm=new JButton("Update");
    JButton Home=new JButton("Back");
    Container c;
    UpdateGuard(String id,String name,String cnic){
        this.id=id;
        this.name=name;
        this.cnic=cnic;
        JFrame frame=new JFrame();
        this.setTitle("Prison Management System");
        this.setBounds(200,20,750,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        c=this.getContentPane();
        c.setLayout(null);

        ImageIcon icon = new ImageIcon("src/prison management.jpg");
        JLabel label = new JLabel(icon);

        JLabel label1 = new JLabel("Guard ID");
        JLabel label2 = new JLabel("Guard Name");
        JLabel label3=new JLabel("Duty Hours");
        JLabel label4=new JLabel("CNIC");
        JLabel label5=new JLabel("Security Number");
        JLabel label6=new JLabel("Jailer ID");
        JLabel label7=new JLabel("Section ID");

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

        int value=100;
        int value2=101;
        int value3=102;
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
        c.add(textField7);

        c.add(box1);
        c.setBackground(Color.gray);
        c.add(label);
        c.add(confirm);


        textfield1.setText(id);
        textfield1.setEditable(false);

        textField2.setText(name);
        textField2.setEditable(false);

        textField4.setText(id);
        textField4.setEditable(false);


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
                if (textField3.getText().isEmpty() || textField5.getText().isEmpty()
                        || textField6.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field cannot be empty");
                }
                else {

                    System.out.println(1);
                    String s3 = textField3.getText();
                    if(Integer.parseInt(s3)>0 && Integer.parseInt(s3)<=24){
                        String s5 = textField5.getText();
                        String s6 = textField6.getText();
                        String sql4 = "select COUNT(*) AS rowcount from jailor where jailor_id=?";
                        PreparedStatement pstt = con.prepareStatement(sql4);
                        pstt.setString(1, s6);
                        ResultSet result = pstt.executeQuery();
                        result.next();
                        int count1=result.getInt("rowcount");
                        result.close();
                        if(count1>0){
                            String s7=box1.getSelectedItem().toString();

                            String sql="update GUARDS set DUTY_HOURs=?,SECURITY_NUMBER=?,JAILOR_JAILOR_ID=?,SECTION_SECTION_ID=?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, s3);
                            pst.setString(2, s5);
                            pst.setString(3, s6);
                            pst.setString(4, s7);
                            ResultSet rs = pst.executeQuery();
                            JOptionPane.showMessageDialog(null,"Record Updated Successfully");
                        }
                        else{

                            JOptionPane.showMessageDialog(null,"Jailer with ID "+s6+" Doest Not exit");

                        }
                    }
                    else{

                        JOptionPane.showMessageDialog(null,"Invalid Hour Provided");

                    }

                }
            }
            else if (e.getSource()==Home){
                dispose();
                new Guard();
            }
        }
        catch(Exception ee){
            JOptionPane.showMessageDialog(null, ee.toString());
        }
    }
}
