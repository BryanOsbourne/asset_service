package co.com.assets_service.security;

import java.util.Map;
import java.util.Date;
import java.security.Key;
import java.util.HashMap;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import javax.crypto.SecretKey;
import io.jsonwebtoken.io.Decoders;
import java.util.function.Function;
import io.jsonwebtoken.security.Keys;
import co.com.assets_service.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import co.com.assets_service.exception.RequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtService {

    @Value("${JWT.SECRET_KEY}")
    private String ACCESS_TOKEN_SECRET;

    public Boolean validateToken(String token, UserDetails userDetails) {
        return (isUserValid(token, userDetails) && !isTokenExpired(token));
    }

    private Boolean isUserValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        if (!username.equals(userDetails.getUsername())) {
            throw new RequestException("400", HttpStatus.BAD_REQUEST, "The user is not valid");
        }
        return true;
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String buildToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        //TODO: Add user roles to claims
        return buildToken(claims, user);
    }

    public String buildToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 8760))
                .signWith(getSignInKey())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        Jws<Claims> parsedJws = Jwts.parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token);
        return parsedJws.getPayload();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
