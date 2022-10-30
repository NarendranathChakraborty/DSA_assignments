
package com.example.demo;



import java.sql.Connection;
import java.sql.DriverManager;


public class LoginDatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection()
    {
        String databaseName = "DSS";


        String username= "omesh";
        String password= "Omesh123@";

        String url = "jdbc:mysql://localhost/" + databaseName ;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,username,password);
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
            // e.getCause();
            System.out.println("not done");
        }


        return databaseLink;
    }

}



