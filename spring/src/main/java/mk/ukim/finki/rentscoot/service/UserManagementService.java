package mk.ukim.finki.rentscoot.service;

import mk.ukim.finki.rentscoot.model.Reservation;
import mk.ukim.finki.rentscoot.model.Role;
import mk.ukim.finki.rentscoot.model.User;

import java.util.List;

public interface UserManagementService {
    User registerUser(String name,String email,String password);

    List<User> findUsersByEmailOrName(String email,String name);

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User updateUser(Long id, String email, String name);

    void deleteUser(Long id);

    boolean changeUserPassword(Long id,String password); //Should be encripted or done trough another secured service

    User setUserRole(Long userId,Role role);

//    User changeUserRole(Long userId,Role role);

}
