package org.studentsphere.controllers;

import org.studentsphere.models.User;
import org.studentsphere.models.UserModel;

import java.util.List;

public class UserController {

    private final UserModel userModel = new UserModel();

    public List<User> getAllUsers() {
        return userModel.getAllUsers();
    }

    public boolean addUser(String username, String email, String password) {
        return userModel.addUser(username, email, password);
    }

    public boolean deleteUser(int userId) {
        return userModel.deleteUser(userId);
    }

    public boolean authenticate(String email, String password) {
        return userModel.authenticate(email, password);
    }

    public boolean resetPassword(int userId, String password) {
        return userModel.resetPassword(userId, password);
    }
}