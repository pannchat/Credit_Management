package capstone.project.credit_manager.config.auth;

import capstone.project.credit_manager.service.AccountService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static final int ONE_HOUR_TO_SECOND = 3600000;

    @Value("${jwt.secret}")
    private String secret;
    private String key;

    private final AccountService accountService;

    @PostConstruct
    protected void init() {
        this.key = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String accountId) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + ONE_HOUR_TO_SECOND);

        return Jwts.builder()
                .claim("accountId", accountId)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    private String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
        return true;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = resolveToken(request);

        if (token == null) {
            return null;
        }

        if (!validateToken(token)) {
            throw new InvalidTokenException();
        }

        Claims claims = getClaims(token);
        UserDetails userDetails = this.accountService.loadUserByUsername(claims.get("accountId").toString());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
