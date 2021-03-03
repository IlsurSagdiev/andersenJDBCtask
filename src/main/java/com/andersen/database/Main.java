package com.andersen.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBConnect connect = new DBConnect();
        List<User> users = new ArrayList<>();

        String querry =
                "SELECT u.name FROM users u INNER JOIN user_role ur ON ur.user_id = u.id WHERE ur.role_id = 2";
        try {
            Statement statement = connect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while(resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString(1));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(users);
    }
}

