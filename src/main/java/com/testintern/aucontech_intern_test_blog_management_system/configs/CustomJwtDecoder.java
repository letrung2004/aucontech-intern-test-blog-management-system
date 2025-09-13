package com.testintern.aucontech_intern_test_blog_management_system.configs;

import java.text.ParseException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;

@Component
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            try {
                if (!signedJWT.verify(new MACVerifier(SIGNER_KEY.getBytes()))) {
                    throw new JwtException("Invalid signature");
                }
            } catch (JOSEException e) {
                throw new JwtException("Invalid signature", e);
            }

            Instant expiration = signedJWT.getJWTClaimsSet().getExpirationTime().toInstant();
            if (expiration.isBefore(Instant.now())) {
                throw new JwtException("Token expired");
            }

            return new Jwt(token,
                    signedJWT.getJWTClaimsSet().getIssueTime().toInstant(),
                    signedJWT.getJWTClaimsSet().getExpirationTime().toInstant(),
                    signedJWT.getHeader().toJSONObject(),
                    signedJWT.getJWTClaimsSet().getClaims());

        } catch (ParseException e) {
            throw new JwtException("Invalid token");
        }
    }
}
