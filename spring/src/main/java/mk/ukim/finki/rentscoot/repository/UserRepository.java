package mk.ukim.finki.rentscoot.repository;

import mk.ukim.finki.rentscoot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User registerUser(User user);

    List<User> findUsersByEmailOrName(String email, String name);

    Optional<User> getUserById(Long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void deleteUser(User user);

}
