package ca.humber.skillswap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import org.springframework.http.client.JdkClientHttpRequestFactory;

@Configuration
public class ApplicationConfig {

    @Bean
    RestTemplate restTemplate() {
        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory();
        requestFactory.setConnectTimeout(Duration.ofSeconds(2));
        requestFactory.setReadTimeout(Duration.ofSeconds(3));
        return new RestTemplate(requestFactory);
    }
}
