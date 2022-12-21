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

public class AddPrisonFrame extends JFrame implements ActionListener, Serializable {
    JTextField textfield1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JTextField textField4 = new JTextField();
    JTextField textField5 = new JTextField();
    JTextField textField6 = new JTextField("2021-01-22");
    JTextField textField7 = new JTextField("2021-01-22");
    JTextField textField8 = new JTextField();
    JTextField textField9 = new JTextField();
    JTextField textField10 = new JTextField();

    JComboBox box1=new JComboBox();
    JComboBox box2=new JComboBox();
    JComboBox box3=new JComboBox();
    JButton confirm=new JButton("Confirm");
    JButton Home=new JButton("Back");
    Container c;
    AddPrisonFrame(){
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

            JLabel label1 = new JLabel("Prisoner ID");
            JLabel label2 = new JLabel("First name");
            JLabel label3 = new JLabel("Last name");
            JLabel label4 = new JLabel("Age");
            JLabel label5 = new JLabel("Address");
            JLabel label6 = new JLabel("Date of In");
            JLabel label7 = new JLabel("Date of Out");
            JLabel label9 = new JLabel("Cell NO");

            label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

            textfield1.setBounds(180, 250, 155, 30);
            label1.setBounds(110, 250, 130, 30);

            textField2.setBounds(180, 290, 155, 30);
            label2.setBounds(110, 290, 80, 30);

            textField3.setBounds(180, 330, 155, 30);
            label3.setBounds(110, 330, 80, 30);


            textField6.setBounds(180, 370, 155, 30);
            label6.setBounds(110, 370, 80, 30);

            textField7.setBounds(510, 250, 140, 30);
            label7.setBounds(420, 250, 130, 30);

            textField4.setBounds(510, 290, 140, 30);
            label4.setBounds(420, 290, 130, 30);

            String sql = "select * from CELL";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                int value=rs.getInt(1);
                box3.addItem(value);

            }

            label9.setBounds(420, 330, 140, 30);
            box3.setBounds(510, 330, 140, 30);

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
            c.add(label9);
            c.add(textField4);
            c.add(textField5);
            c.add(textField6);
            c.add(textField7);

            c.add(box3);
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

        }
    }
    @Override

    public void actionPerformed(ActionEvent e) {
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DBMS", "12345");

            if (e.getSource() == confirm) {
                if (textfield1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty() || textField4.getText().isEmpty()
                        || textField6.getText().isEmpty() || textField7.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field cannot be empty");
                }
                else {
                    String s1 = textfield1.getText();
                    String s2 = textField2.getText();
                    String s3 = textField3.getText();
                    String s4 = textField4.getText();
                    String s6 = textField6.getText();
                    String s7 = textField7.getText();
                    String s8 = textField8.getText();
                    String s9 = textField9.getText();
                    String s10 = textField10.getText();

                    String sql1 = "select COUNT(*) AS rowcount from Prisoners where Prisoner_id=?";
                    PreparedStatement pst1 = con.prepareStatement(sql1);

                    pst1.setString(1, s1);

                    ResultSet rs1 = pst1.executeQuery();
                    rs1.next();
                    int count=rs1.getInt("rowcount");
                    rs1.close();
                    if(count!=0){
                        JOptionPane.showMessageDialog(null, "Prison with this ID Already exist\nTry Any other ID");
                    }
                    else{

                        String s13 =  box3.getSelectedItem().toString();
                        String sqll="select* from CELL where CELL_NO=?";
                        PreparedStatement pstt=con.prepareStatement(sqll);
                        pstt.setString(1,s13);
                        ResultSet resultSet1=pstt.executeQuery();
                        int prison=0;
                        int limit=0;
                        while (resultSet1.next()){
                            prison=resultSet1.getInt(3);
                            limit=resultSet1.getInt(5);
                        }
                        if(prison<limit){
                            prison++;
                            String sqlup="update CELL set NO_PRISONERS=? where CELL_NO=?";
                            PreparedStatement pstup=con.prepareStatement(sqlup);
                            pstup.setInt(1,prison );
                            pstup.setString(2,s13);
                            pstup.executeQuery();
                            String sql2 = "insert into Prisoners values (?,?,?,?,?,?,?)";

                            PreparedStatement pst2 = con.prepareStatement(sql2);
                            pst2.setString(1, s1);
                            pst2.setString(2, s2);
                            pst2.setString(3, s3);
                            pst2.setString(4, s4);

                            pst2.setDate(5, java.sql.Date.valueOf(s6));
                            pst2.setDate(6, java.sql.Date.valueOf(s7));

                            pst2.setString(7,s13);
                            ResultSet resultSet=pst2.executeQuery();

                            JOptionPane.showMessageDialog(null, "Prisoner Added Successfully");

                            dispose();
                            new Prisoner();
                        }

                        else{
                            JOptionPane.showMessageDialog(null, "Cell No "+s13+" cannot accommodate More Prisons \nTry any other Cell NO");
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
