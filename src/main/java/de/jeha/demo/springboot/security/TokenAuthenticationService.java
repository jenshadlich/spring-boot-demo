package de.jeha.demo.springboot.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.emptyList;

class TokenAuthenticationService {

    private static final long EXPIRATION_TIME = TimeUnit.HOURS.toMillis(24);
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    static void addAuthentication(HttpServletResponse res, String username) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        final ClassPathResource resource = new ClassPathResource("demo.jks");
        final KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(resource.getInputStream(), "password".toCharArray());

        final Key key = keystore.getKey("demo", "password".toCharArray());

        final String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.RS512, key)
                .compact();

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
    }

    static Authentication getAuthentication(HttpServletRequest request) throws Exception {
        final ClassPathResource resource = new ClassPathResource("demo.jks");
        final KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(resource.getInputStream(), "password".toCharArray());
        final PublicKey publicKey = keystore.getCertificate("demo").getPublicKey();

        final String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            final String user = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }

}