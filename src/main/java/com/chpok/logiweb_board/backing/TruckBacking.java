package com.chpok.logiweb_board.backing;

import com.chpok.logiweb_board.model.Location;
import com.chpok.logiweb_board.model.Truck;
import com.chpok.logiweb_board.service.LocationService;
import com.chpok.logiweb_board.service.TruckService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;

@Named
@RequestScoped
public class TruckBacking {
    private List<Location> availableLocations;
    private String specifiedLocationName;

    @EJB
    private TruckService truckService;

    @EJB
    private LocationService locationService;

    @PostConstruct
    private void init() {
        availableLocations = locationService.getAvailableLocations();

        if (!availableLocations.isEmpty()) {
            specifiedLocationName = availableLocations.get(0).getName();
        }
    }

    public String getSpecifiedLocationName() {
        return specifiedLocationName;
    }

    public void setSpecifiedLocationName(String specifiedLocationName) {
        this.specifiedLocationName = specifiedLocationName;
    }

    public List<Truck> getTrucksAtSpecifiedLocation() {
        if (specifiedLocationName != null) {
            return truckService.getTrucksAtLocation(specifiedLocationName);
        } else {
            return Collections.emptyList();
        }
    }

    public List<Location> getAvailableLocations() {
        return availableLocations;
    }
}
