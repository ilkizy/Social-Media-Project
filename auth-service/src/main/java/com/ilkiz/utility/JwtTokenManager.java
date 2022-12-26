package com.ilkiz.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ilkiz.exception.AuthServiceException;
import com.ilkiz.exception.ErrorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    @Value("${myjwt.secretkey}")
    private String secretKey;
    @Value("${myjwt.audience}")
    private String audience;
    @Value("${myjwt.issuer}")
    private String issuer;

    public String createToken(Long authid){
        String token = null;
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            token = JWT.create()
                    .withAudience(audience) // Kitle
                    .withIssuer(issuer) //Yayımcı
                    .withIssuedAt(new Date()) // Oluşturma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis()+(1000*60))) //Geçersiz kılınma zamanı (60 sn sonra)
                    .withClaim("authid", authid) // kullanılacak bilgiler
                    .sign(algorithm); //şifreleme - imzalama işlemi yapılır
            return (token);
        }catch (Exception e){
        return null;
    }
}

    public Optional<Long> getByIdFromToken(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withAudience(audience) // Kitle
                    .withIssuer(issuer) // Yayımcı
                    .build(); // kod oluşturulur.
            DecodedJWT decodedToken = jwtVerifier.verify(token); // tokenın geçerliliği doğrulur.
            if(decodedToken==null) throw new AuthServiceException(ErrorType.GECERSIZ_TOKEN);
            Long authid = decodedToken.getClaim("authid").asLong();
            return  Optional.of(authid);
        }catch (Exception e){
            throw new AuthServiceException(ErrorType.GECERSIZ_TOKEN);
        }
    }

}
