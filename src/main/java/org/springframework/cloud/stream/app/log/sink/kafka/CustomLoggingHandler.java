package org.springframework.cloud.stream.app.log.sink.kafka;

import org.springframework.cloud.stream.app.log.sink.kafka.controller.MaxSizeHashMap;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.Message;

/**
 * Created by anbabans on 11/27/2017.
 */
public class CustomLoggingHandler extends LoggingHandler{
    public CustomLoggingHandler(String level) {
        super(level);
    }

    @Override
    protected void handleMessageInternal(Message<?> message) throws Exception {
        super.handleMessageInternal(message);
        MaxSizeHashMap.instance.put(message.toString(), message.getPayload().toString());
    }
}
