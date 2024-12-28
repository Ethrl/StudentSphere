package org.studentsphere.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty username;
    private final SimpleStringProperty email;
    private final SimpleStringProperty password;

    public User() {
        this.id = new SimpleIntegerProperty(0);
        this.username = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
    }

    public User(int id, String username, String email, String password) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
    }

    public User(int id, String username, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty("");
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public int getId() {
        return id.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPassword() {
        return password.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}