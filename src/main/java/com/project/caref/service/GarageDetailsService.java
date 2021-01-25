package com.project.caref.service;

import com.project.caref.models.Garage;
import com.project.caref.models.User;
import com.project.caref.models.UserGarage;
import com.project.caref.models.dto.GarageDto;
import com.project.caref.repository.GarageRepository;
import com.project.caref.repository.UserGarageRepository;
import com.project.caref.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GarageDetailsService {

    @Autowired
    private GarageRepository garageRepository;
    @Autowired
    private UserGarageRepository userGarageRepository;
    @Autowired
    private UserRepository userRepository;

    public Garage save(GarageDto garage) {
        Garage newGarage = new Garage();
        newGarage.setGarageAddress(garage.getGarageAddress());
        newGarage.setGarageName(garage.getGarageName());
        newGarage = garageRepository.save(newGarage);

        if(!garage.getUsers().isEmpty()) {
            Garage finalNewGarage = newGarage;
            garage.getUsers().forEach(garId -> {
                User user = userRepository.getOne(garId);
                UserGarage userGarage = new UserGarage();
                userGarage.setUser(user);
                userGarage.setGarage(finalNewGarage);
                userGarageRepository.save(userGarage);
            });
        }
        return garageRepository.getOne(newGarage.getId());
    }
}
