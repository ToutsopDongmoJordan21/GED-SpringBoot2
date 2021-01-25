package com.project.caref.service;

import com.project.caref.exeption.ResourceNotFoundException;
import com.project.caref.models.dto.CarResponseDto;
import com.project.caref.files.dto.FileDto;
import com.project.caref.files.services.FileService;
import com.project.caref.models.*;
import com.project.caref.models.dto.CarDto;
import com.project.caref.repository.*;
import com.project.caref.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarDetailsService {
    
    private final CarRepository carRepository;
    private final CarAccessoireRepository carAccessoireRepository;
    private final AccessoireRepository accesoireRepository;
    private final FileService fileService;
    private final  UserRepository userRepository;

    public Car save(CarDto car){
        Car newCar = new Car();
        newCar.setCarTitle(car.getCarTitle());
        newCar.setCarBrand(car.getCarBrand());
        newCar.setCarOverview(car.getCarOverview());
        newCar.setCarLoanPrice(car.getCarLoanPrice());
        newCar.setCarFuel(car.getCarFuel());
        newCar.setCarYearModel(car.getCarYearModel());
        newCar.setCarSeating(car.getCarSeating());
        newCar.setCarPrice(car.getCarPrice());
        newCar.setCarAddedDate(new Date());

        System.out.println("date is" + newCar.getCarAddedDate());

        //save new car
        newCar = carRepository.save(newCar);

        if(!car.getAccessors().isEmpty()) {
            Car finalNewCar = newCar;
            car.getAccessors().forEach(accId -> {
                Accessoire accessoire = accesoireRepository.getOne(accId);
                CarAccessoire carAccessoire = new CarAccessoire();
                carAccessoire.setAccessoire(accessoire);
                carAccessoire.setCar(finalNewCar);
                carAccessoireRepository.save(carAccessoire);
            });
        }
        return carRepository.getOne(newCar.getId());
    }

    public CarResponseDto findOneCarById(Long carId) {
        Car car = carRepository.getOne(carId);
        Optional<User> optionalUser = userRepository.findByUsername(car.getCreatedBy());
        List<CarAccessoire> accessors = carAccessoireRepository.findByCar(car);
        List<FileDto> files = fileService.findCarFile(carId);
        return CarResponseDto.CarResponseDtoBuilder.aCarResponseDto()
                .withId(carId)
                .withCarTitle(car.getCarTitle())
                .withCarFuel(car.getCarFuel())
                .withCarBrand(car.getCarBrand())
                .withCarAddedDate(car.getCarAddedDate())
                .withCarLoanPrice(car.getCarLoanPrice())
                .withCarPrice(car.getCarPrice())
                .withCarOverview(car.getCarOverview())
                .withCarSeating(car.getCarSeating())
                .withCarYearModel(car.getCarYearModel())
                .withPostById(optionalUser.map(User::getId).orElse(null))
                .withPostByName(optionalUser.map(User::getUsername).orElse(null))
                .withAccessorsId(accessors.isEmpty() ? null : accessors.stream().map(CarAccessoire::getAccessoire).map(Accessoire::getId).collect(Collectors.toList()))
                .withAccessorsName(accessors.isEmpty() ? null : accessors.stream().map(CarAccessoire::getAccessoire).map(Accessoire::getAccessoireName).collect(Collectors.toList()))
                .withCarImages(files)
                .build();
    }

    public List<CarResponseDto> findAllCar() {
        return carRepository.findAll().stream().map(buildListCarResponseDto()).collect(Collectors.toList());
    }

    private Function<Car, CarResponseDto> buildListCarResponseDto() {
        return car -> findOneCarById(car.getId());
    }
}
