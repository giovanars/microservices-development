package br.com.kitchen.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;

public class SlackIntegrationService {
    private static final String HOOKS_URL = "https://hooks.slack.com/services/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";

    public void sendMessage() throws JsonProcessingException {


        String userWebhookUrl = String.format(HOOKS_URL, userChannelId);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);



        ObjectMapper objectMapper = new ObjectMapper();

        String messageJson = objectMapper.writeValueAsString(message);

        HttpEntity<String> entity = new HttpEntity<>(messageJson, headers);

        restTemplate.exchange(userWebhookUrl, HttpMethod.POST, entity, String.class);

    }
}
