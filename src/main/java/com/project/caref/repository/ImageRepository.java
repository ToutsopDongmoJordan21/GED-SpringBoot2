package com.project.caref.repository;

import com.project.caref.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
   // @Override
    Optional<Image> findByName(String imagename);
}
