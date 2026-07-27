package ca.humber.skillswap.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class VerificationServiceClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String username;
    private final String password;

    public VerificationServiceClient(RestTemplate restTemplate,
                                     @Value("${skillswap.verification-service.base-url}") String baseUrl,
                                     @Value("${skillswap.verification-service.username}") String username,
                                     @Value("${skillswap.verification-service.password}") String password) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    public Optional<List<VerificationRecordDto>> findAll() {
        try {
            ResponseEntity<VerificationRecordDto[]> response = restTemplate.exchange(
                    baseUrl,
                    HttpMethod.GET,
                    authorizedEntity(null),
                    VerificationRecordDto[].class
            );
            VerificationRecordDto[] body = response.getBody();
            return Optional.of(body == null ? List.of() : Arrays.asList(body));
        } catch (RestClientException ex) {
            return Optional.empty();
        }
    }

    public boolean create(VerificationRequest request) {
        try {
            restTemplate.exchange(baseUrl, HttpMethod.POST, authorizedEntity(request), VerificationRecordDto.class);
            return true;
        } catch (RestClientException ex) {
            return false;
        }
    }

    public boolean delete(Long id) {
        try {
            restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE, authorizedEntity(null), Void.class);
            return true;
        } catch (RestClientException ex) {
            return false;
        }
    }

    private <T> HttpEntity<T> authorizedEntity(T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        if (body != null) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        return new HttpEntity<>(body, headers);
    }
}
