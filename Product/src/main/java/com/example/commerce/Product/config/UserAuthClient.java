package com.example.commerce.Product.config;

import com.example.commerce.Product.model.DTO.Response.ResponseData;
import com.example.commerce.Product.model.DTO.Response.UserAuthDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Import(WebClientConfig.class)
public class UserAuthClient {
    private final ObjectMapper mapper;
    private final WebClient webClient;

    public UserAuthClient(WebClient webClient, ObjectMapper mapper) {
        this.webClient = webClient;
        this.mapper = mapper;
    }

    public UserAuthDto validateAndGetUser(String token) {
        try {
            ResponseData response = this.webClient.post()
                    .uri("/api/v1/user/validate-user")
                    .header("Authorization", token)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> Mono.error(new RuntimeException("Auth failed")))
                    .bodyToMono(ResponseData.class)
                    .block();

            if (response == null || response.getData() == null) {
                return null;
            }

            return mapper.convertValue(response.getData(), UserAuthDto.class);

        } catch (Exception e) {
            return null; // Safely deny access if user-service is down or rejects the token
        }
    }

}
