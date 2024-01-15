package me.abeilles.chess.pl.controllers;

import me.abeilles.chess.bll.user.UserService;
import me.abeilles.chess.pl.model.auth.AuthDTO;
import me.abeilles.chess.pl.model.auth.LoginForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public AuthDTO login(@RequestBody LoginForm form){
        return userService.login(form);
    }



}
