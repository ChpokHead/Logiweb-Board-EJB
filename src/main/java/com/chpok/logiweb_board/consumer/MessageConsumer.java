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
    private static final String GET_TRUCK_URL = "http://localhost:8080/employeeTruck/trucks/%d";
    private static final String ID_PART_OF_MESSAGE = "id = ";

    @EJB
    private TruckDao truckDao;

    @Consumer(topics = "logiweb", groupId = "logiweb-group")
    public void onMessage(final String message) {
        LOGGER.info("New message: {}", message);

        if (message.contains("truck updated")) {
            onTruckUpdated(message);
        } else if (message.contains("truck deleted")) {
            onTruckDeleted(message);
        } else if (message.contains("truck saved")) {
            onTruckSaved();
        }
    }

    private void onTruckUpdated(String message) {
        final int idIndex = message.indexOf(ID_PART_OF_MESSAGE);
        final int id = Integer.parseInt(message.substring(idIndex + ID_PART_OF_MESSAGE.length()));

        truckDao.update(ClientBuilder
                .newClient()
                .target(String.format(GET_TRUCK_URL, id))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<Truck>(){}));
    }

    private void onTruckSaved() {
        truckDao.deleteAll();

        truckDao.saveList(ClientBuilder
                .newClient()
                .target(GET_TRUCKS_URL)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Truck>>(){}));
    }

    public void onTruckDeleted(String message) {
        final int idIndex = message.indexOf(ID_PART_OF_MESSAGE);
        final int id = Integer.parseInt(message.substring(idIndex + ID_PART_OF_MESSAGE.length()));

        truckDao.delete((long) id);
    }

}
