package mk.finki.ukim.lab.service.domain;

import mk.finki.ukim.lab.model.domain.User;
import mk.finki.ukim.lab.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> register(String username, String password, String repeatPassword, String name, String surname, Role role);

    Optional<User> login(String username, String password);

    Optional<User> findByUsername(String username);

}
