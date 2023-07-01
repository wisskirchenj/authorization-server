package de.cofinpro.auth.service;

import de.cofinpro.auth.user.User;
import de.cofinpro.auth.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final UserRepository repository;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    /**
     * method receives and saves the User entity with data mapped from the UserDto (name and encrypted password),
     * @param user the prepared User entity to save to the database.
     * @throws UserAlreadyExistsException if user already exists.
     */
    public void registerUser(User user) throws UserAlreadyExistsException {
        if (repository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException();
        }
        repository.save(user);
    }
}
