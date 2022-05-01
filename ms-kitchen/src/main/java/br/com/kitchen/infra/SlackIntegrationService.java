package br.com.kitchen.infra;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface SlackIntegrationService {
    void SendMessage(String orderId) throws JsonProcessingException;
}
