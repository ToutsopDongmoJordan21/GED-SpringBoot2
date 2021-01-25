package com.project.caref.repository;

import com.project.caref.models.UserGarage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGarageRepository extends JpaRepository<UserGarage, Long> {
}
