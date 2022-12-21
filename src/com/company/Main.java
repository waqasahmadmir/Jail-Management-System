package com.company;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends Component {
    public static void main(String[] args) {
	// write your code here
        try{
             Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DBMS", "12345");
            new SignIn();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
