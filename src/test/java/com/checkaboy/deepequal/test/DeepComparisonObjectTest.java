package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.comparator.FieldComparator;
import com.checkaboy.deepequal.comparator.ObjectComparator;
import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.interf.IObjectComparator;
import com.checkaboy.deepequal.model.car.Car;
import com.checkaboy.deepequal.model.car.ETransmissionType;
import com.checkaboy.deepequal.model.car.Engine;
import com.checkaboy.deepequal.model.car.Transmission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class DeepComparisonObjectTest {

    @Test
    public void testPrimitiveFieldCompare() {
        Car carBmvI3 = new Car();
        carBmvI3.setCarBrand("BMW");
        carBmvI3.setModel("I3");

        Car carBmvI8 = new Car();
        carBmvI8.setCarBrand("BMW");
        carBmvI8.setModel("I8");

        Car carMercedesAMG63 = new Car();
        carMercedesAMG63.setCarBrand("Mercedes");
        carMercedesAMG63.setModel("AMG 63");

        FieldComparator<Car, String> fieldComparator = new FieldComparator<>(Car::getCarBrand, Objects::equals);
        Assertions.assertTrue(fieldComparator.equal(carBmvI3, carBmvI8));
        Assertions.assertFalse(fieldComparator.equal(carBmvI3, carMercedesAMG63));
    }

    @Test
    public void testObjectEqual() {
        IFieldComparator<Car> objectComparator = createCarComparator();

        Car carBmvI3 = createBmvI3();
        Car carBmvX5M = createBmwX5M();

        Assertions.assertFalse(objectComparator.equal(carBmvI3, carBmvX5M));
    }

    @Test
    public void testObjectEqualWithNPE() {
        IFieldComparator<Car> objectComparator = createCarComparator();

        Car carBmvI3 = createBmvI3();
        Car carBmvX5M = createBmwX5M();
        carBmvX5M.setTransmission(null);
        carBmvX5M.setEngine(null);

        Assertions.assertFalse(objectComparator.equal(carBmvI3, carBmvX5M));
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

    private IFieldComparator<Car> createCarComparator() {
        IObjectComparator<Car> carComparator = new ObjectComparator<>();

        carComparator.put("carBrand", FieldComparator.simpleFieldComparator(Car::getCarBrand));
        carComparator.put("model", FieldComparator.simpleFieldComparator(Car::getModel));
        carComparator.put("color", FieldComparator.simpleFieldComparator(Car::getColor));
        carComparator.put("doorCount", FieldComparator.simpleFieldComparator(Car::getDoorCount));

        {
            IObjectComparator<Engine> engineComparator = new ObjectComparator<>();
            engineComparator.put("horsePower", FieldComparator.simpleFieldComparator(Engine::getHorsePower));
            engineComparator.put("volume", FieldComparator.simpleFieldComparator(Engine::getVolume));
            engineComparator.put("countCylinder", FieldComparator.simpleFieldComparator(Engine::getCountCylinder));
            FieldComparator<Car, Engine> comparator = new FieldComparator<>(Car::getEngine, engineComparator);
            carComparator.put("engine", comparator);
        }

        {
            IObjectComparator<Transmission> transmissionComparator = new ObjectComparator<>();
            transmissionComparator.put("countSteps", FieldComparator.simpleFieldComparator(Transmission::getCountSteps));
            transmissionComparator.put("transmissionType", FieldComparator.simpleFieldComparator(Transmission::getTransmissionType));
            FieldComparator<Car, Transmission> comparator = new FieldComparator<>(Car::getTransmission, transmissionComparator);
            carComparator.put("transmissionType", comparator);
        }

        return carComparator;
    }

}
