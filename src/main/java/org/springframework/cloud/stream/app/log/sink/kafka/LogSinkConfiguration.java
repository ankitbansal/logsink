package org.springframework.cloud.stream.app.log.sink.kafka;

/**
 * Created by anbabans on 11/27/2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.app.log.sink.LogSinkProperties;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.handler.LoggingHandler;

/**
 * @author Dave Syer
 * @author Marius Bogoevici
 * @author Gary Russell
 */
@EnableBinding(Sink.class)
@EnableConfigurationProperties(LogSinkProperties.class)
public class LogSinkConfiguration {

    @Autowired
    private LogSinkProperties properties;

    @Bean
    @ServiceActivator(inputChannel = Sink.INPUT)
    public LoggingHandler logSinkHandler() {
        CustomLoggingHandler loggingHandler = new CustomLoggingHandler(this.properties.getLevel().name());
        loggingHandler.setExpression(this.properties.getExpression());
        loggingHandler.setLoggerName(this.properties.getName());
        return loggingHandler;
    }

}
