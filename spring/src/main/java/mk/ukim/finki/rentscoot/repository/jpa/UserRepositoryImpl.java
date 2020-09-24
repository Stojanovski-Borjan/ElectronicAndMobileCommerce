package mk.ukim.finki.rentscoot.repository.jpa;

import mk.ukim.finki.rentscoot.model.User;
import mk.ukim.finki.rentscoot.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository repository;

    public UserRepositoryImpl(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User registerUser(User user) {
        return this.repository.save(user);
    }

    @Override
    public List<User> findUsersByEmailOrName(String email, String name) {
        return this.repository.findByEmailOrNameOrderByName(email, name);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.repository.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    @Override
    public void deleteUser(User user) {
        this.repository.delete(user);
    }
}
