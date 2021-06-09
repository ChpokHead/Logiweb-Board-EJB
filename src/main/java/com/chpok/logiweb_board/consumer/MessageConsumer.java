package com.chpok.logiweb_board.consumer;

import com.chpok.logiweb_board.dao.TruckDao;
import com.chpok.logiweb_board.model.Truck;
import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

@KafkaConfig(bootstrapServers = "localhost:9092")
@Stateless
public class MessageConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);
    private static final String GET_TRUCKS_URL = "http://localhost:8080/employeeTruck/trucks";

    @EJB
    private TruckDao truckDao;

    @Consumer(topics = "logiweb", groupId = "logiweb-group")
    public void onMessage(final String message) {
        LOGGER.info("New message: {}", message);

        truckDao.deleteAll();

        truckDao.save(ClientBuilder
                .newClient()
                .target(GET_TRUCKS_URL)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Truck>>(){}));
    }

}
