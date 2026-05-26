package com.example.commerce.Product.config;

import com.example.commerce.Product.model.DTO.Response.ResponseData;
import com.example.commerce.Product.model.DTO.Response.UserAuthDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserAuthClient {
    private ObjectMapper mapper;
    private final WebClient webClient;

    public UserAuthClient(WebClient webClient) {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
    }

    public Boolean isValidUser(String token) {
        try {
            Object isValid = this.webClient.get()
                    .uri("/api/v1/user/validate-user") // Assuming this endpoint exists on user-service
                    .header("Authorization", token)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> Mono.error(new RuntimeException("Auth failed")))
                    .bodyToMono(Boolean.class)
                    .block(); // Blocking here converts the reactive Mono back to a standard synchronous response

            ResponseData data = mapper.convertValue(isValid, ResponseData.class);

            UserAuthDto dto = mapper.convertValue(data, UserAuthDto.class);

            return !StringUtils.isEmpty(dto.getEmailId());
        } catch (Exception e) {
            return false; // Safely deny access if user-service is down or rejects the token
        }
    }

}
