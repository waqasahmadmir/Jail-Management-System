package com.company;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewGuard extends JFrame implements ActionListener, Serializable {

    Container c;
    JButton back=new JButton("Back");
    public ViewGuard(){

        this.add(back);
        back.setBounds(380, 600, 100, 20);
        back.addActionListener(this::actionPerformed);

        this.setVisible(false);

        funtion();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DBMS", "12345");

           if(e.getSource()==back){
               dispose();
               new Guard();
           }
        }
        catch(Exception ee){
            JOptionPane.showMessageDialog(null, ee.toString());
        }
    }
    public void funtion(){
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DBMS", "12345");
            setSize(900, 900);

            String sql1 = "select count(*) AS rowcount from GUARDS";
            String sql = "select*  from GUARDS  order  by GUARD_ID";
            PreparedStatement pst1 = con.prepareStatement(sql1);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs1 = pst1.executeQuery();
            ResultSet rs = pst.executeQuery();
            rs1.next();
            int count = rs1.getInt("rowcount");
            rs1.close();
            Object[][] data=null;
            JTable table=new JTable();
            DefaultTableModel model = new DefaultTableModel(new String[] { "Guard Id", "Guard Name", "Duty Hours", "CNIC", "Jailer ID", "Section ID", "Salary" },0);
            table.setModel(model);
            if(count>0){
                while (rs.next()){
                    model.addRow( new Object[]

                            {rs.getString(1), rs.getString(2), rs.getString(3)
                                    , rs.getString(4), rs.getInt(5), rs.getInt(6),rs.getInt(7)
                            }
                    );
                }
                table.setBounds(200, 20, 750, 600);
                //add the table to the frame
                table.setSize(800, 800);
                this.add(new JScrollPane(table));
                this.setTitle("View Guards");
                this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                this.setResizable(true);
                this.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "No Guard Exist in System");
                dispose();
                new Guard ();
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
}
