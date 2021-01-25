package com.project.caref.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "garage")
public class Garage {

    @OneToMany
    private Set<UserGarage> userGarage = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String garageName;

    private String garageAddress;

    public Garage() {}

    public Garage(Long id,
                  String garageName,
                  String garageAddress) {
        this.id = id;
        this.garageName = garageName;
        this.garageAddress = garageAddress;
    }

    public Set<UserGarage> getUserGarage() { return userGarage; }
}
