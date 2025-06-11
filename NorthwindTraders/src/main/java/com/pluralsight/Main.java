package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException,ClassNotFoundException {

        String connectionString = "jdbc:mysql://localhost:3306/northwind";
        String username = "root";
        String password = "yearup";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection;
        connection = DriverManager.getConnection(connectionString, username, password);

        Statement statement = connection.createStatement();

        String query = """
                SELECT ProductName\s
                FROM products\s
               """;

        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String product = results.getString("ProductName");
            System.out.println(product);
        }

        connection.close();
    }
}