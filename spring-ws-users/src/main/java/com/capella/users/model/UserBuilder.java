package com.capella.users.model;

/**
 * @author ramesh
 */
public class UserBuilder {
    private Long id;
    private String username;
    private String password;

    private UserBuilder() {
    }

    public static UserBuilder getInstance() {
        return new UserBuilder();
    }

    public UserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
