package ABSMovies.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

   @Value("${jwtSecretKey}")
   private String jwtSecret = "ChangeThisSecretToSomethingReallySecret!";
   private final Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

    public String generateJwtToken(String email) {
        return JWT.create()
                .withSubject(email)
                .sign(algorithm);
    }

    public String getUserNameFromJwtToken(String token){
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }

    public DecodedJWT validateJwtToken(String authToken){
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(authToken);
        return jwt;
    }
}