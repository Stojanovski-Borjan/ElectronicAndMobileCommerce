package mk.ukim.finki.rentscoot.service.implementation;

import mk.ukim.finki.rentscoot.model.Role;
import mk.ukim.finki.rentscoot.model.User;
import mk.ukim.finki.rentscoot.model.exceptions.InvalidUserException;
import mk.ukim.finki.rentscoot.repository.UserRepository;
import mk.ukim.finki.rentscoot.service.UserManagementService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserManagementServiceImplementation implements UserManagementService {
    private final UserRepository userRepository;

    public UserManagementServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String name, String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRole(Role.CLIENT);
        return this.userRepository.registerUser(user);
    }

    @Override
    public List<User> findUsersByEmailOrName(String email, String name) {
        return this.userRepository.findUsersByEmailOrName(email,name);
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.getUserById(id).orElseThrow(InvalidUserException::new);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    @Override
    public User updateUser(Long id, String email, String name) {
        User user = this.userRepository.getUserById(id).orElseThrow(InvalidUserException::new);
        user.setEmail(email);
        user.setName(name);
        return this.userRepository.registerUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = this.userRepository.getUserById(id).orElseThrow(InvalidUserException::new);
        this.userRepository.deleteUser(user);
    }

    @Override
    public boolean changeUserPassword(Long id, String password) {
        User user = this.userRepository.getUserById(id).orElseThrow(InvalidUserException::new);
        if( password.length() > 6) {
            user.setPassword(password);
            this.userRepository.registerUser(user);
            return true;
        }
        else return false;
    }

    @Override
    public User setUserRole(Long userId, Role role) {
        User user = this.userRepository.getUserById(userId).orElseThrow(InvalidUserException::new);
        user.setRole(role);
        return this.userRepository.registerUser(user);
    }

//    @Override
//    public User changeUserRole(Long userId, Role role) {
//        return null;
//    }
}
