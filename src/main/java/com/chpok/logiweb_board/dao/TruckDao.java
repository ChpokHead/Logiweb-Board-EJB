package com.chpok.logiweb_board.dao;

import com.chpok.logiweb_board.model.Truck;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TruckDao {
    private static final String FIND_ALL_QUERY = "SELECT t FROM Truck t";
    private static final String FIND_ALL_BY_CURRENT_LOCATION_ID_QUERY = "SELECT t FROM Truck t where t.location.id = :id";
    private static final String DELETE_ALL_QUERY = "DELETE FROM Truck";

    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;

    public List<Truck> findAll() {
        return entityManager.createQuery(FIND_ALL_QUERY, Truck.class).getResultList();
    }

    public void save(List<Truck> savingTrucks) {
        for (Truck truck : savingTrucks) {
            entityManager.persist(truck);
        }

        entityManager.flush();
    }

    public void delete(Long id) {
        final Truck deletingTruck = entityManager.find(Truck.class, id);

        entityManager.remove(deletingTruck);

        entityManager.flush();
    }

    public void deleteAll() {
        entityManager.createQuery(DELETE_ALL_QUERY).executeUpdate();

        entityManager.flush();
    }

    public List<Truck> findByCurrentLocationId(Long currentLocationId) {
        return entityManager.createQuery(FIND_ALL_BY_CURRENT_LOCATION_ID_QUERY, Truck.class)
                .setParameter("id", currentLocationId)
                .getResultList();
    }
}
