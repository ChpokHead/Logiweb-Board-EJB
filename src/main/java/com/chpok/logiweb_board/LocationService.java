package com.chpok.logiweb_board;

import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
public class LocationService {
    public List<Location> getAvailableLocations() {
        return ClientBuilder
                .newClient()
                .target("http://localhost:8080/locations")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Location>>(){});
    }
}
