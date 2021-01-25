package com.project.caref.models.dto;

import java.util.List;

public class GarageDto {

    private List<Long> users;

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public String getGarageAddress() {
        return garageAddress;
    }

    public void setGarageAddress(String garageAddress) {
        this.garageAddress = garageAddress;
    }

    public GarageDto(String garageName, String garageAddress) {
        this.garageName = garageName;
        this.garageAddress = garageAddress;
    }

    public List<Long> getUsers() {
        return users;
    }

    public GarageDto() {}

    private String garageName;

    private String garageAddress;
}
