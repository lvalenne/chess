package me.abeilles.chess.bll.user;

import me.abeilles.chess.pl.model.auth.AuthDTO;
import me.abeilles.chess.pl.model.auth.LoginForm;

public interface UserService {
    AuthDTO login(LoginForm form);
}
