package com.pluralsight;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String connectionString = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "yearup";

        // Load mysql driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection;
        connection = DriverManager.getConnection(connectionString, username, password);

        Statement statement = connection.createStatement();

        String query = "SELECT city FROM city " +
                "WHERE country_id = 103";

        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String city = results.getString("city");
            System.out.println(city);
        }

        connection.close();

    }
}