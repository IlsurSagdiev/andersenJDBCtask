package com.andersen.database.entity;

import java.util.Objects;

public class User {

    private Integer id;
    private String name;
    private RoleEnum role;
    private String email;
    private String password;

    public User() {
    }

    public User(String name, RoleEnum role, String email, String password) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
