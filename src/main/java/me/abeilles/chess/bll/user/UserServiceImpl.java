package me.abeilles.chess.bll.user;

import me.abeilles.chess.dal.repositories.UserRepository;
import me.abeilles.chess.pl.config.security.JWTProvider;
import me.abeilles.chess.pl.model.auth.AuthDTO;
import me.abeilles.chess.pl.model.auth.LoginForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final JWTProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JWTProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthDTO login(LoginForm form) {
        return null;
    }
}
