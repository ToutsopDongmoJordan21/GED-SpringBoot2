package com.project.caref.repository;

import com.project.caref.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    public Car findByCarTitle(String carTitle);

    public Car findById(long id);

    public List<Car> findAll();

    public long count();
}
