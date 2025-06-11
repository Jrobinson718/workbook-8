package com.pluralsight;

import java.sql.*;

public class Main {

    private static  sqlConnectionInfo sqlConnectionInfo;

    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        if (args.length != 3) {
            System.out.println("Application needs two arguments to run:" +
                    "java com.pluralsight.Main <username> <password>");
            System.exit(1);
        }

        sqlConnectionInfo = getSqlConnectionInfoFromArgs(args);

        try{
            displayCities(103);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static sqlConnectionInfo getSqlConnectionInfoFromArgs(String[] args) {
        // get username and password from the command line args
        String username = args[0];
        String password = args[1];
        String connectionString = args[2];

        return new sqlConnectionInfo(connectionString, username, password);
    }
    public static void displayCities(int countryID) throws SQLException, ClassNotFoundException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet results = null;

        try {
            // Loads MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Opens a connection to the database
            connection = DriverManager.getConnection(
                    sqlConnectionInfo.getConnectionString(),
                    sqlConnectionInfo.getUsername(),
                    sqlConnectionInfo.getPassword());

            // Query
            String query = "SELECT city FROM city " +
                    "WHERE country_id = ?";

            ps = connection.prepareStatement(query);
            ps.setInt(1, countryID);
            results = ps.executeQuery();

            while (results.next()) {
                String city = results.getString("city");
                System.out.println(city);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (results != null) results.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
    }
}