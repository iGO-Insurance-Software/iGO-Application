package Dao;
import Customer.Customer;

import java.sql.*;

public class Dao {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void connect() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/insurance?serverTimezone=UTC&useSSL=false", "root", "utku1989");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void execute(String query) {
        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(String query) {
        try {
            statement = connect.createStatement();
            if (!statement.execute(query))
                System.out.println("insert completed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet retrieve(String query) throws SQLException {
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public void update(String query) {
        try {
            statement = connect.createStatement();
            if(!statement.execute(query))
                System.out.println("update completed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(String query) {
        try {
            statement = connect.createStatement();
            if(!statement.execute(query))
                System.out.println("delete completed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteAll(String query) {
        try {
        statement = connect.createStatement();
        if (!statement.execute(query))
            System.out.println("deleteAll completed");
        } catch(SQLException e) {
         e.printStackTrace();
        }
    }
}
