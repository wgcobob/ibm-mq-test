package com.vasscompany.mq_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class JwtDecoderConfig {
    @Bean
    public JwtDecoder jwtDecoder(){

        byte[] keyBytes = Base64.getDecoder().decode("SFRU/TKNnLyKcUEMyVxO82c5gatSHbvHBWYAlyQ90HI=");
        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA256");

        NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(key).build();

        OAuth2TokenValidator<Jwt> withTimeStamp = new JwtTimestampValidator();
        decoder.setJwtValidator(withTimeStamp);

        return decoder;
    }
}
