package com.chpok.logiweb_board.dao;

import com.chpok.logiweb_board.model.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class LocationDao {
    private static final String FIND_ALL_QUERY = "SELECT l FROM Location l";
    private static final String FIND_BY_NAME_QUERY = "SELECT l FROM Location l WHERE l.name = :name";

    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;

    public List<Location> findAll() {
        return entityManager.createQuery(FIND_ALL_QUERY, Location.class).getResultList();
    }

    public Optional<Location> findByName(String name) {
        final List<Location> foundLocations = entityManager.createQuery(FIND_BY_NAME_QUERY, Location.class)
                .setParameter("name", name).getResultList();

        if (foundLocations.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(foundLocations.get(0));
        }

    }

}
