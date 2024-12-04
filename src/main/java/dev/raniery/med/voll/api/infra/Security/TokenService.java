package dev.raniery.med.voll.api.infra.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String gerarToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            String token = JWT.create()
                    .withIssuer("Med API")
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token");
        }
    }
}
