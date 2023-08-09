package com.starking.client;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * @author pedroRhamon
 */
@RestController
public class ClientController {

	@GetMapping("home")
	public Mono<String> home(
			@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
		return Mono.just("""
				<h2>Acess Token: %s</h2>
				<h2>Refresh Token: %s</h2>
				<h2>Id Token: %s</h2>
				<h2>Claims: %s</h2>
				""".formatted(client.getAccessToken().getTokenValue()));
	}
}
