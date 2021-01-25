
package com.project.caref.models;

import javax.persistence.*;

@Entity
@Table(name = "user_garage")
public class UserGarage {
    public UserGarage(Long id, User user, Garage garage) {
        this.id = id;
        this.user = user;
        this.garage = garage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id",
            referencedColumnName = "id")
    private Garage garage;

    public UserGarage() {}
}

