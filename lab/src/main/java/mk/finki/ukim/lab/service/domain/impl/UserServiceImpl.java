package mk.finki.ukim.lab.service.domain.impl;

import mk.finki.ukim.lab.model.domain.User;
import mk.finki.ukim.lab.model.enums.Role;
import mk.finki.ukim.lab.model.exceptions.*;
import mk.finki.ukim.lab.repository.UserRepository;
import mk.finki.ukim.lab.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.of(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username)));
    }

    @Override
    public Optional<User> register(
            String username,
            String password,
            String repeatPassword,
            String name,
            String surname,
            Role userRole
    ) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword)) throw new PasswordsDoNotMatchException();
        if (userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, passwordEncoder.encode(password), name, surname, userRole);
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return Optional.of(userRepository.findByUsernameAndPassword(username, password).orElseThrow(
                InvalidUserCredentialsException::new));
    }

}
