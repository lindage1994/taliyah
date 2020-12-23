package org.iahsnil.auth.authentication.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Josh Cummings
 */
@RestController
public class OAuth2ResourceServerController {

	@GetMapping("/")
	public String index(@AuthenticationPrincipal Jwt jwt) {
		return String.format("Hello, %s!", jwt.getSubject());
	}

	@GetMapping("/message")
	public String message() {
		return "secret message";
	}
}
