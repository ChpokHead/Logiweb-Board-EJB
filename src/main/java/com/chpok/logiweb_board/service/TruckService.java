package com.chpok.logiweb_board.service;

import com.chpok.logiweb_board.dao.LocationDao;
import com.chpok.logiweb_board.dao.TruckDao;
import com.chpok.logiweb_board.model.Location;
import com.chpok.logiweb_board.model.Truck;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Stateless
public class TruckService {
    @EJB
    public LocationDao locationDao;

    @EJB
    private TruckDao truckDao;

    public List<Truck> getTrucksAtLocation(String locationName) {
        Optional<Location> location = locationDao.findByName(locationName);

        if (location.isPresent()) {
            return truckDao.findByCurrentLocationId(location.get().getId());
        } else {
            return Collections.emptyList();
        }
    }

}
