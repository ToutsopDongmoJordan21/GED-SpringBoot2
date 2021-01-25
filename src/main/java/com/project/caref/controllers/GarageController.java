package com.project.caref.controllers;

import com.project.caref.exeption.ResourceNotFoundException;
import com.project.caref.models.Garage;
import com.project.caref.models.dto.GarageDto;
import com.project.caref.repository.GarageRepository;
import com.project.caref.service.GarageDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class GarageController {
    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private GarageDetailsService garageDetailsService;

    @GetMapping("/garage")
    public List<Garage> getAllAllGarage() {

        return garageRepository.findAll();
    }

    @GetMapping("/garage/{id}")
    public ResponseEntity<Garage> getGarageById(@PathVariable(value = "id") Long garageId)
        throws ResourceNotFoundException {
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new ResourceNotFoundException("Garage not found for this id ::" +garageId));
        return ResponseEntity.ok().body(garage);
    }

    @PostMapping("/garage")
    public ResponseEntity<?> saveGarage(@Valid @RequestBody GarageDto garage) throws Exception {
        return ResponseEntity.ok(garageDetailsService.save(garage));
    }

    @DeleteMapping("/garage/{id}")
    public Map<String, Boolean> deleteGarage(@PathVariable(value = "id") Long garageId)
        throws ResourceNotFoundException {
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new ResourceNotFoundException("garage not found for this id :: " +garageId));

        garageRepository.delete(garage);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Garage was successful delete", Boolean.TRUE);
        return response;
    }

    @PutMapping("/garage/{id}")
    public ResponseEntity<Garage> updateGarage(@PathVariable(value = "id") Long garageId,
                                               @RequestBody Garage garageDetails) throws ResourceNotFoundException {
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new ResourceNotFoundException("Garage not found for this :: " +garageId));

        garage.setGarageName(garageDetails.getGarageName());
        garage.setGarageAddress(garageDetails.getGarageAddress());
        final Garage updateGarage = garageRepository.save(garage);
        return ResponseEntity.ok(updateGarage);
    }

}
