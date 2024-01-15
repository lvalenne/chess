package me.abeilles.chess.pl.model.auth;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
@Data
@Builder
public class LoginForm {
    private String login;
    private String email;
    private String password;
}
