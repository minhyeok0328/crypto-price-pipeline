package com.crypto.streaming.exchange;

import com.crypto.streaming.abstracts.Exchange;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UPBit implements Exchange {
    private final WebSocketClient webSocketClient;

    public UPBit(@Qualifier("upBitWebSocketClient") WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    @Override
    public void start() throws InterruptedException {
        webSocketClient.connect();

        while (!webSocketClient.isOpen()) {
            Thread.sleep(100);
        }

        String ticketData = "[{\"ticket\":\"NaD9uy7XrLRXKz1gA1wVxyGbNV4YbPUaCOdSATKA\"},{\"type\":\"ty\",\"codes\":[\"KRW-BTC\"]},{\"format\":\"DEFAULT\"}]";
        webSocketClient.send(ticketData);
    }
}
