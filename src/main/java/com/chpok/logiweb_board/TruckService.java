package com.chpok.logiweb_board;

import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
public class TruckService {
    private static final String GET_TRUCKS_AT_LOCATION_URL = "http://localhost:8080/employeeTruck/trucks?location=%s";

    public List<Truck> getTrucksAtLocation(String locationName) {
        return ClientBuilder
                .newClient()
                .target(String.format(GET_TRUCKS_AT_LOCATION_URL, locationName))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Truck>>(){});
    }

}
