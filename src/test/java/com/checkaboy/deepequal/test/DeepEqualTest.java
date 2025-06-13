package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.FieldEq;
import com.checkaboy.deepequal.ObjectEq;
import com.checkaboy.deepequal.SimpleFieldEq;
import com.checkaboy.deepequal.SubClassEq;
import com.checkaboy.deepequal.model.Car;
import com.checkaboy.deepequal.model.ETransmissionType;
import com.checkaboy.deepequal.model.Engine;
import com.checkaboy.deepequal.model.Transmission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class DeepEqualTest {

    @Test
    public void testSimpleFieldEqual() {
        FieldEq<Car, String> fieldEq = new FieldEq<>(Car::getCarBrand, Objects::equals);

        Car carBmvI3 = new Car();
        carBmvI3.setCarBrand("BMW");
        carBmvI3.setModel("I3");

        Car carBmvI8 = new Car();
        carBmvI8.setCarBrand("BMW");
        carBmvI8.setModel("I8");

        Car carMercedesAMG63 = new Car();
        carMercedesAMG63.setCarBrand("Mercedes");
        carMercedesAMG63.setModel("AMG 63");

        Assertions.assertTrue(fieldEq.equal(carBmvI3, carBmvI8));

        Assertions.assertFalse(fieldEq.equal(carBmvI3, carMercedesAMG63));
    }

    @Test
    public void testSimpleObjectEqual() {
        ObjectEq<Car> eq = new ObjectEq<>();
        eq.put("carBrand", new SimpleFieldEq<>(Car::getCarBrand));
        eq.put("model", new SimpleFieldEq<>(Car::getModel));
        eq.put("color", new SimpleFieldEq<>(Car::getColor));
        eq.put("doorCount", new SimpleFieldEq<>(Car::getDoorCount));
        eq.put("engine", new SubClassEq<>(Car::getEngine, new ObjectEq<>()));

        Car carBmvI3 = createBmvI3();
        Car carBmvX5M = createBmwX5M();


    }

    private Car createBmvI3() {
        Car car = new Car();
        car.setCarBrand("BMW");
        car.setModel("I3");
        car.setColor("Red");
        car.setDoorCount(5);
        {
            Engine engine = new Engine();
            engine.setCountCylinder(0);
            engine.setVolume(0);
            engine.setHorsePower(238);
            car.setEngine(engine);
        }
        {
            Transmission transmission = new Transmission();
            transmission.setTransmissionType(ETransmissionType.AUTOMATIC);
            transmission.setCountSteps(1);
            car.setTransmission(transmission);
        }

        return car;
    }

    private Car createBmwX5M() {
        Car car = new Car();
        car.setCarBrand("BMW");
        car.setModel("X5 M");
        car.setColor("Black");
        car.setDoorCount(5);
        {
            Engine engine = new Engine();
            engine.setCountCylinder(8);
            engine.setVolume(3.2f);
            engine.setHorsePower(550);
            car.setEngine(engine);
        }
        {
            Transmission transmission = new Transmission();
            transmission.setTransmissionType(ETransmissionType.AUTOMATIC);
            transmission.setCountSteps(8);
            car.setTransmission(transmission);
        }
        return car;
    }

}
