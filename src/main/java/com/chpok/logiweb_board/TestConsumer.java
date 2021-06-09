package com.chpok.logiweb_board;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaConfig(bootstrapServers = "localhost:9092")
public class TestConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConsumer.class);

    @Consumer(topics = "logiweb", groupId = "logiweb-group")
    public void onMessage(final String key, final String value) {
        LOGGER.info("We got this value: " + value);
    }
}
