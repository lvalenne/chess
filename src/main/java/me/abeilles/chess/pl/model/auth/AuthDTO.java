package me.abeilles.chess.pl.model.auth;

import lombok.Builder;
import lombok.Data;
import me.abeilles.chess.dal.entities.UserRole;

import java.util.Set;

@Data
@Builder
public class AuthDTO {
    private String login;
    private String email;
    private String token;
    private Set<UserRole> roles;
}
