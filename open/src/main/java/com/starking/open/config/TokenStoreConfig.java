package com.starking.open.config;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

	private KeyPair generateRsaKey() {
		// TODO Auto-generated method stub
		return null;
	}
}
