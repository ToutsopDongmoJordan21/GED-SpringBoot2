package com.project.caref.service;

import com.project.caref.models.Accessoire;
import com.project.caref.models.dto.AccessoireDto;
import com.project.caref.repository.AccessoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessoireDetailsService {

    @Autowired
    private AccessoireRepository Accessoire;

    public Accessoire save(AccessoireDto accessoire) {
        Accessoire newAccessoire = new Accessoire();
        newAccessoire.setAccessoireName(accessoire.getAccessoireName());
        return Accessoire.save(newAccessoire);
    }
}
