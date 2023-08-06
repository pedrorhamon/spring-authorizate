package com.starking.open.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

/**
 * @author pedroRhamon
 */

@Configuration
public class TokenStoreConfig {

	@Bean
	public JWKSource<SecurityContext> jwkSource() {
		KeyPair pair = generateRsaKey();
		RSAPublicKey publicKey = (RSAPublicKey) pair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();
		RSAKey key = new RSAKey.Builder(publicKey)
				.privateKey(privateKey)
				.keyID(UUID.randomUUID().toString())
				.build();
		JWKSet jwtSet = new JWKSet(key);
		return new ImmutableJWKSet<>(jwtSet);
	}
	
	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwtSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwtSource);
	}
	

	private static KeyPair generateRsaKey() {
		KeyPair keyPair;

		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

		return keyPair;
	}
}
