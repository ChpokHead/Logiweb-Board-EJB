package com.chpok.logiweb_board.consumer;

import com.chpok.logiweb_board.dao.TruckDao;
import com.chpok.logiweb_board.model.Truck;
import com.chpok.logiweb_board.model.kafka.LogiwebMessage;
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
    private static final String GET_TRUCK_URL = "http://localhost:8080/employeeTruck/trucks/%d";

    @EJB
    private TruckDao truckDao;

    @Consumer(topics = "logiweb-truck", groupId = "logiweb-group")
    public void onMessage(final LogiwebMessage message) {
        LOGGER.info("New message: {} with id {}", message.getEntityEventMessage(), message.getEntityId());

        switch (message.getEntityEventMessage()) {
            case "truck saved":
                onTruckSaved(message.getEntityId());
                break;
            case "truck updated":
                onTruckUpdated(message.getEntityId());
                break;
            case "truck deleted":
                onTruckDeleted(message.getEntityId());
                break;
            default:
                break;
        }
    }

    private void onTruckUpdated(Long entityId) {
        truckDao.update(ClientBuilder
                .newClient()
                .target(String.format(GET_TRUCK_URL, entityId))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<Truck>(){}));
    }

    private void onTruckSaved(Long entityId) {
        truckDao.save(ClientBuilder
                .newClient()
                .target(String.format(GET_TRUCK_URL, entityId))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<Truck>(){}));
    }

    public void onTruckDeleted(Long entityId) {
        truckDao.delete(entityId);
    }

}
