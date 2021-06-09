package com.chpok.logiweb_board.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "truck")
public class Truck {
    @Id
    private Long id;

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "drivers_shift")
    private Short driversShift;

    @Column(name = "capacity")
    private Short capacity;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public Short getDriversShift() {
        return driversShift;
    }

    public Short getCapacity() {
        return capacity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setDriversShift(Short driversShift) {
        this.driversShift = driversShift;
    }

    public void setCapacity(Short capacity) {
        this.capacity = capacity;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return Objects.equals(regNumber, truck.regNumber) && Objects.equals(driversShift, truck.driversShift) && Objects.equals(capacity, truck.capacity) && status == truck.status && Objects.equals(location, truck.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber, driversShift, capacity, status, location);
    }
}
