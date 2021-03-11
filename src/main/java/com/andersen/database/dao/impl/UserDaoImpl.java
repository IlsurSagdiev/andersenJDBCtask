package com.andersen.database.dao.impl;

import com.andersen.database.entity.RoleEnum;
import com.andersen.database.util.DataBaseConnect;
import com.andersen.database.dao.UserDao;
import com.andersen.database.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final String saveQuery = "INSERT INTO users(name,email,password) " +
            "VALUES(?,?,?);";

    private final String saveUserRole = "INSERT INTO user_role(user_id, role_id) " +
            "VALUES (?,?);";

    private final String getRoleIdByName = "SELECT id FROM roles r WHERE r.name = ?;";

    private final String getQuery = "SELECT users.name AS roles.name FROM users u" +
            "JOIN user_role ur ON u.id = ur.user_id " +
            "JOIN roles r ON ur.role_id = r.id " +
            "WHERE u.id = ?;";

    private final String getNameByEmailQuery = "SELECT users.email FROM users u WHERE u.email = ?;";

    private final String getAllQuery = "SELECT users.id, users.name, users.email, roles.name AS role " +
            "FROM users u" +
            "JOIN user_role ur ON u.id = ur.user_id " +
            "JOIN roles r ON ur.role_id = r.id;";

    private final String updateName = "UPDATE users u SET u.name = ?, WHERE u.id=  ?;";

    private final String updateUserRole = "UPDATE user_role ur SET ur.role_id = ? WHERE ur.user_id=?;";

    private final String deleteQuery = "DELETE FROM users WHERE id = ?;";

    private final String getUserByEmail = "SELECT id, email, password, role FROM users WHERE email = ?";


    @Override
    public void save(User user) {
        DataBaseConnect connect = new DataBaseConnect();
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getRoleIdByName);
            statement.setString(1, String.valueOf(user.getRole()));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int roleId = resultSet.getInt("id");
                statement = connection.prepareStatement(saveQuery);
                statement.setInt(1, user.getId());
                statement.setInt(2, user.getId());
                statement.setString(3, user.getName());
                statement.execute();

                statement = connection.prepareStatement(saveUserRole);
                statement.setInt(1, user.getId());
                statement.setInt(2, roleId);
                statement.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {

        DataBaseConnect connect = new DataBaseConnect();
        User user = null;
        try (Connection connection = connect.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getUserByEmail);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole((RoleEnum) (resultSet.getObject(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User get(Integer id) {

        DataBaseConnect connect = new DataBaseConnect();

        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getQuery);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<User> getAllUsers() {
        DataBaseConnect connect = new DataBaseConnect();
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getAllQuery);

            ResultSet resultSet = statement.executeQuery();
            List<User> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(getUserFromResultSet(resultSet));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    @Override
    public void update(User user) {
        DataBaseConnect connect = new DataBaseConnect();
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getRoleIdByName);
            statement.setString(1, String.valueOf(user.getRole()));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int roleId = resultSet.getInt("id");

                statement = connection.prepareStatement(updateName);
                statement.setInt(3, user.getId());
                statement.setString(1, user.getName());
                statement.execute();

                statement = connection.prepareStatement(updateUserRole);
                statement.setInt(1, roleId);
                statement.setInt(2, user.getId());
                statement.execute();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        DataBaseConnect connect = new DataBaseConnect();
        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        RoleEnum role = (RoleEnum) (resultSet.getObject("role"));
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new User(name, role, email, password);
    }

    @Override
    public boolean isExists(User user) {

        DataBaseConnect connect = new DataBaseConnect();
        String email = user.getEmail();

        try (Connection connection = connect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getNameByEmailQuery);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}