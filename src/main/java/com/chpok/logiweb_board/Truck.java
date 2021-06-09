package com.chpok.logiweb_board;

public class Truck {
    private Long id;
    private String regNumber;
    private Short driversShift;
    private Short capacity;
    private Integer status;

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
}
