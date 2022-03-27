package com.training.vehicles.services;

import com.training.vehicles.entities.Vehicle;
import com.training.vehicles.repositories.VehicleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

//@SpringBootTest
@ExtendWith(MockitoExtension.class) // Dzięki temu testy wykonuj? się szybciej. Cały kontekst springa nie jest podnoszony
class VehicleServiceImplTest {

    private VehicleService vehicleService;

    @BeforeEach
    public void setUpRepository() {

        VehicleRepository vehicleRepository = mock(VehicleRepository.class);
        Vehicle v1 = new Vehicle();
        v1.setColor("red");
        v1.setBrand("Audi");
        v1.setModel("A6");
        v1.setId(1L);

        Vehicle v2 = new Vehicle();
        v2.setColor("green");
        v2.setBrand("Audi");
        v2.setModel("A4");
        v2.setId(2L);

        Iterable<Vehicle> all = Arrays.asList(v1, v2);
        doReturn(all).when(vehicleRepository).findAll();

        vehicleService = new VehicleServiceImpl(vehicleRepository);
    }

    @Test
    void shouldReturnSelectedColor() {

        // when
        List<Vehicle> actual = vehicleService.findAllVehiclesByColor("green");

        // then
        assertEquals("green", actual.get(0).getColor());
        assertEquals("Audi", actual.get(0).getBrand());
        assertEquals("A4", actual.get(0).getModel());
        assertEquals(2L, actual.get(0).getId());
    }

    @Test
    void shouldReturnSelectedColor2() {

        // when
        List<Vehicle> actual = vehicleService.findAllVehiclesByColor("green");

        // then
        Assertions.assertEquals("green", actual.get(0).getColor());
        Assertions.assertEquals("Audi", actual.get(0).getBrand());
        Assertions.assertEquals("A4", actual.get(0).getModel());
        Assertions.assertEquals(2L, actual.get(0).getId());
    }

}