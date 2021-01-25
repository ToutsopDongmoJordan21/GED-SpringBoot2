package com.project.caref.repository;

import com.project.caref.models.Garagimage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GaragimageRepository extends JpaRepository<Garagimage, String> {

}
