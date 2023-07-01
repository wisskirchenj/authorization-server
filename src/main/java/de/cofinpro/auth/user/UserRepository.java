package de.cofinpro.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    /**
     * method to implement the functional interface of the Authentication Manager bean.
     * @param username the authentication provided username as search key
     * @return the User entity as UserDetails type to suit the interface if found, or else Optional.empty()
     */
    Optional<UserDetails> findByUsername(String username);
}
