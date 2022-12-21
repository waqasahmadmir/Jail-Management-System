package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.*;
import java.util.Scanner;

public class Report extends JFrame implements ActionListener,Serializable {


    JButton Home=new JButton("Back");
    JButton report=new JButton("Report");

    JTextField textField = new JTextField();

    private JOptionPane OptionPane;
    Container c;
    Report(){

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

        JLabel label1 = new JLabel("Enter Prison ID");


        textField.setBounds(335, 300, 160, 30);
        label1.setBounds(240, 300, 130, 30);

        Home.setBounds(450, 400, 80, 20);
        report.setBounds(335, 400, 80, 20);

        Font myFont1 = new Font("Serif", Font.ITALIC | Font.BOLD, 12);

        Cursor curse1 = new Cursor(Cursor.HAND_CURSOR);

        Home.setCursor(curse1);
        Home.addActionListener(this::actionPerformed);
        c.add(Home);

        report.setCursor(curse1);
        report.addActionListener(this::actionPerformed);
        c.add(report);


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
                new Prisoner();

            }
            else if (e.getSource() == report) {
                String sql = "select COUNT(*) AS rowcount from PRISONERS where PRISONER_ID=?";

                String s1=textField.getText();
                PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, s1);
                ResultSet res1 = pst.executeQuery();
                res1.next();
                int count=res1.getInt("rowcount");
                res1.close();
                if(count!=0){
                    String r4="";

                    String sqlr1="select* from prisoners,cell where prisoners.cell_cell_no=cell.cell_no and prisoners.prisoner_id=?";
                    PreparedStatement pstr1=con.prepareStatement(sqlr1);
                    pstr1.setString(1,s1);
                    ResultSet resultSet=pstr1.executeQuery();
                    while (resultSet.next()){
                        String r1="\t\tPERSONAL DETAILS\n"+"Prisoner ID : "+resultSet.getString(1)+"\n"+"First Name : "+resultSet.getString(2)
                                +"\n"+"Last Name : "+resultSet.getString(3)+"\n"+"Age   : "+resultSet.getString(4)
                                +"\n"+"Date of IN : "+resultSet.getDate(5)+"\n"+"Date of Out : "+resultSet.getDate(6)+"\n"+"\t\tCELL DETAILS"
                                +"\n"+"Cell NO : "+resultSet.getString(7)+"\n"+"Cell Name : "+resultSet.getString(9)
                                +"\n"+"Section ID : "+resultSet.getString(11)+"\n";

                        r4=r4+r1;

                    }

                    String sqlr2="select * from prisoners,case where prisoners.prisoner_id=case.prisoners_prisoner_id and prisoners.prisoner_id=?";
                    PreparedStatement pstr2=con.prepareStatement(sqlr2);
                    pstr2.setString(1,s1);

                    ResultSet resultSet1=pstr2.executeQuery();

                    while ((resultSet1.next())){
                        String r2="\t\tCASES DETAILS\n"+"Case ID : "+resultSet1.getString(8)
                                +"\nCase Type : "+resultSet1.getString(9)+"\nCase Year : "+resultSet1.getString(10)+"\n";
                        r4=r4+r2;
                    }

                    String sqlr3="select * from crime ,commited where crime.crime_id=commited.crime_crime_id and commited.prisoners_prisoner_id=?";
                    PreparedStatement pstr3=con.prepareStatement(sqlr3);
                    pstr3.setString(1,s1);
                    ResultSet resultSet2 =pstr3.executeQuery();

                    while (resultSet2.next()){
                       String r3="\t\tCRIME DETAILS"+"\nCrime ID : "+resultSet2.getString(1)+"\nCrime Name : "+resultSet2.getString(2)
                        +"\nCrime Type : "+resultSet2.getString(3)+"\nCrime Description: "+resultSet2.getString(4)+"\n";
                       r4=r4+r3;
                    }

                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    frame.setPreferredSize(new Dimension(450, 600));
                    JTextArea textArea = new JTextArea(30, 40);
                    textArea.setText(r4);
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    JOptionPane.showMessageDialog(frame, scrollPane);

                }
                else {

                    JOptionPane.showMessageDialog(null,"Prison With ID "+s1+" Does not Exist");

                }
            }

        }
        catch (Exception ee) {
            JOptionPane.showMessageDialog(new JFrame(),ee.toString());
        }
    }
}
