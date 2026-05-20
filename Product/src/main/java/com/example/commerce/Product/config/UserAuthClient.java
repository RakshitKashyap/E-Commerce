package com.example.commerce.Product.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserAuthClient {

    private final WebClient webClient;

    public boolean isValidUser(String token) {
        try {
            Boolean isValid = this.webClient.get()
                    .uri("/v1/iam/users/validate") // Assuming this endpoint exists on user-service
                    .header("Authorization", token)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> Mono.error(new RuntimeException("Auth failed")))
                    .bodyToMono(Boolean.class)
                    .block(); // Blocking here converts the reactive Mono back to a standard synchronous response

            return isValid != null && isValid;
        } catch (Exception e) {
            return false; // Safely deny access if user-service is down or rejects the token
        }
    }

}
