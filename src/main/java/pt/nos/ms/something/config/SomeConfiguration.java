package pt.nos.ms.something.config;

import java.util.Collections;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SomeConfiguration {
    
    @Bean
    public RestTemplate createRestTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.rootUri("http://localhost:8080/api/v1/somethings").build();
        restTemplate.setInterceptors(Collections.singletonList(new HttpInterceptor()));
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
        return restTemplate;
    }
    
}
