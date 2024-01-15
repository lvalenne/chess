package me.abeilles.chess.pl.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import me.abeilles.chess.dal.entities.User;
import me.abeilles.chess.dal.entities.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class JWTProvider {
    private static final String JWT_SECRET = "YTZ`p8@C2W6+b'u[BsEzNtG<jh!RkF;~)°,PD}nM4U*#^xA_$5)";

    private static final long EXPIRES_AT = 900000;

    private static final String AUTH_HEADER = "Authorization";

    private static final String TOKEN_PREFIX = "Bearer ";

    private final UserDetailsService userDetailsService;

    public JWTProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(String pseudo, String email, List<UserRole> roles){
        return TOKEN_PREFIX + JWT.create()
            .withSubject(pseudo)
            .withClaim("email", email)
            .withClaim("roles",roles.stream().map(Enum::toString).toList())
            .withExpiresAt(Instant.now().plusMillis(EXPIRES_AT))
            .sign(Algorithm.HMAC512(JWT_SECRET));
    }
    public String extractToken(HttpServletRequest request){
        String header = request.getHeader(AUTH_HEADER);

        if(header == null || !header.startsWith(TOKEN_PREFIX))
            return null;

        return header.replaceFirst(TOKEN_PREFIX,"");
    }

    public boolean validateToken(String token){

        try {


            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(JWT_SECRET))
                    .acceptExpiresAt(EXPIRES_AT)
                    .withClaimPresence("sub")
                    .withClaimPresence("email")
                    .withClaimPresence("roles")
                    .build()
                    .verify(token);


            String pseudo = jwt.getSubject();
            User user = (User) userDetailsService.loadUserByUsername(pseudo);
            if (!user.isEnabled())
                return false;


            List<UserRole> tokenRoles = jwt.getClaim("roles").asList(UserRole.class);

            return user.getRoles().containsAll(tokenRoles);


        }catch (JWTVerificationException | UsernameNotFoundException ex) {
            return false;
        }
    }
    public Authentication createAuthentication(String token){
        DecodedJWT jwt = JWT.decode(token);

        String username = jwt.getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }
}
