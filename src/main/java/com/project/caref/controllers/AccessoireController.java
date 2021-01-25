package com.project.caref.controllers;

import com.project.caref.exeption.ResourceNotFoundException;
import com.project.caref.models.Accessoire;
import com.project.caref.models.dto.AccessoireDto;
import com.project.caref.repository.AccessoireRepository;
import com.project.caref.service.AccessoireDetailsService;
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
public class AccessoireController {
    @Autowired
    private AccessoireRepository accessoireRepository;

    @Autowired
    private AccessoireDetailsService accessoireDetailsService;

    @GetMapping("/accessoires")
    public List<Accessoire> getAllAccessoires() {

        return accessoireRepository.findAll();
    }

    @GetMapping("/accessoires/{id}")
    public ResponseEntity<Accessoire> getAccessoireById(@PathVariable(value = "id") Long accessoireId)
        throws ResourceNotFoundException {
        Accessoire accessoire = accessoireRepository.findById(accessoireId)
                .orElseThrow(() -> new ResourceNotFoundException("Accessoire not found for this id ::" +accessoireId));
        return ResponseEntity.ok().body(accessoire);
    }

    /*@PostMapping("/accessoires")
    public Accessoire createAccessoire( @RequestBody Accessoire accessoire){
        return accessoireRepository.save(accessoire);
    } */

    @PostMapping(value = "/accessoires")
    public ResponseEntity<?> saveAccessoires(@Valid @RequestBody AccessoireDto accessoire) throws Exception {
        return ResponseEntity.ok(accessoireDetailsService.save(accessoire));
    }

    @DeleteMapping("/accessoires/{id}")
    public Map<String, Boolean> deleteAccessoire(@PathVariable(value = "id") Long accessoireId)
        throws ResourceNotFoundException {
        Accessoire accessoire = accessoireRepository.findById(accessoireId)
                .orElseThrow(() -> new ResourceNotFoundException("Accessoire not found for this id :: " +accessoireId));

        accessoireRepository.delete(accessoire);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Accessoire was successful delete", Boolean.TRUE);
        return response;
    }

    @PutMapping("/accessoires/{id}")
    public ResponseEntity<Accessoire> updateAccessoire(@PathVariable(value = "id") Long accessoireId,
                                                       @RequestBody Accessoire accessoireDetails) throws ResourceNotFoundException {
        Accessoire accessoire = accessoireRepository.findById(accessoireId)
                .orElseThrow(() -> new ResourceNotFoundException("Accessiore not found for this :: " +accessoireId));

        accessoire.setAccessoireName(accessoireDetails.getAccessoireName());
        final Accessoire updateAccessoire = accessoireRepository.save(accessoire);
        return ResponseEntity.ok(updateAccessoire);
    }

}
