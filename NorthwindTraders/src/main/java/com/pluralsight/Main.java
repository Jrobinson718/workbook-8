package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        if (args.length != 2) {
            System.out.println("Application needs two arguments to run:" +
                    "java com.pluralsight.Main <username> <password>");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        displayCities(103, username, password);
    }

    public static void displayCities(int countryID, String username, String password) throws SQLException, ClassNotFoundException {

        String connectionString = "jdbc:mysql://localhost:3306/sakila";
        // Loads MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Opens a connection to the database using the url to point to the correct one.
        Connection connection;
        connection = DriverManager.getConnection(connectionString, username, password);

        // Query
        String query = "SELECT city FROM city " +
                "WHERE country_id = ?";

        // Create statement
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, countryID);

        // Executes Query
        ResultSet results = ps.executeQuery();

        // Process results
        while (results.next()) {
            String city = results.getString("city");
            System.out.println(city);
        }
        results.close();
        ps.close();
        connection.close();
    }
}