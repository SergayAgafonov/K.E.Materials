package com.example.materialscompany;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBaseHandler {

    Connection dbConnection;
    public Connection getDbConnection () throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://127.0.0.1:3306/datauser";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, "root", "MySQL");

        return dbConnection;
    }

    public void signUpUser (User user) {

        String insert = "INSERT INTO " + Constants.USER_TABLE + "(" + Constants.USERS_name + "," + Constants.USERS_family + "," + Constants.USERS_login + "," + Constants.USERS_password + ")" + "VALUES (?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,user.getname());
            prSt.setString(2,user.getfamily());
            prSt.setString(3,user.getlogin());
            prSt.setString(4,user.getpassword());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Constants.USER_TABLE + " WHERE " + Constants.USERS_login + "=? AND " + Constants.USERS_password + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getlogin());
            prSt.setString(2,user.getpassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}
