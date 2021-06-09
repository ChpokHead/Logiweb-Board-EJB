package com.chpok.logiweb_board.service;

import com.chpok.logiweb_board.dao.LocationDao;
import com.chpok.logiweb_board.model.Location;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class LocationService {
    @EJB
    private LocationDao locationDao;

    public List<Location> getAvailableLocations() {
        return locationDao.findAll();
    }

}
