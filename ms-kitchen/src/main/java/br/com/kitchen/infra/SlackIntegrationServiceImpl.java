package br.com.kitchen.infra;

import br.com.kitchen.model.dtos.SlackRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

@Service
public class SlackIntegrationServiceImpl implements SlackIntegrationService {

    @Value("${slack.webhook}")
    private String slackWebhook;

    @Value("${slack.username}")
    private String slackUsername;

    @Value("${slack.channel}")
    private String slackChannel;

    @Value("${slack.icon.emoji}")
    private String slackIconEmoji;

    @Override
    public void SendMessage(String orderId) throws JsonProcessingException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(slackWebhook);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(formatMessage(orderId));

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            client.execute(httpPost);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SlackRequestDto formatMessage(String orderId){
        return new SlackRequestDto(slackUsername, slackChannel, slackIconEmoji, "Pedido" + orderId + "recebido!");
    }
}
