package me.abeilles.chess.bll.user;


import me.abeilles.chess.dal.entities.User;
import me.abeilles.chess.dal.repositories.UserRepository;
import me.abeilles.chess.pl.config.security.JWTProvider;
import me.abeilles.chess.pl.model.auth.AuthDTO;
import me.abeilles.chess.pl.model.auth.LoginForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getLogin(),form.getPassword()));

        User user = userRepository.findByPseudoOrEmail((form.getLogin()), form.getLogin()).get();

        String token = jwtProvider.generateToken(user.getUsername(), user.getEmail(),List.copyOf(user.getRoles()));

        return AuthDTO.builder()
                .token(token)
                .login(user.getPseudo())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }



}
