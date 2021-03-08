package com.andersen.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CRUDOperation {

    public static void addUser(String name, String role) {
        DBConnect connect = new DBConnect();

        String query1 = "INSERT INTO users (name)  value ('" + name + " ')";
        String query2 = "INSERT INTO user_role(user_id, role_id)" +
                " values((select id FROM users u where u.name = '" + name + "')," + role + ");";
        Statement statement = null;
        try {
            statement = connect.getConnection().createStatement();
            statement.execute(query1);
            statement.execute(query2);

            connect.getConnection().close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<User> getAllUsersByRole(String roleId) {
        DBConnect connect = new DBConnect();
        List<User> users = new ArrayList<>();

        String query =
                "SELECT u.name FROM users u INNER JOIN user_role ur" +
                        " ON ur.user_id = u.id WHERE ur.role_id = " + roleId;
        Statement statement = null;
        try {
            statement = connect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString(1));
                users.add(user);
            }
            connect.getConnection().close();
            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public static void removeUser(String name) {
        DBConnect connect = new DBConnect();

        String query1 = "DELETE FROM user_role WHERE user_id" +
                "  = (SELECT id FROM users WHERE name = '" + name + "' )";
        String query2 = "DELETE FROM users u WHERE u.name = '" + name + "'";

        Statement statement = null;

        try {
            statement = connect.getConnection().createStatement();
            statement.execute(query1);
            statement.execute(query2);

            connect.getConnection().close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void renameUser(String currentName, String newName) {
        DBConnect connect = new DBConnect();

        String query = "UPDATE users u SET u.name = '" + newName
                + "' WHERE t.name = '" + currentName + "'";
        Statement statement = null;

        try {
            statement = connect.getConnection().createStatement();
            statement.execute(query);

            connect.getConnection().close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
