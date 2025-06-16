package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.model.FieldComparator;
import com.checkaboy.deepequal.builder.general.ObjectComparatorBuilder;
import com.checkaboy.deepequal.builder.general.SubObjectComparatorBuilder;
import com.checkaboy.deepequal.builder.safe.FieldSafeComparatorBuilder;
import com.checkaboy.deepequal.builder.safe.SubObjectSafeComparatorBuilder;
import com.checkaboy.deepequal.wrapper.NPESafeFunctionWrapper;
import com.checkaboy.deepequal.model.interf.IComparator;
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
        IComparator<Car> objectComparator = createCarComparator();

        Car carBmvI3 = createBmvI3();
        Car carBmvX5M = createBmwX5M();

        Assertions.assertFalse(objectComparator.equal(carBmvI3, carBmvX5M));
    }

    @Test
    public void testObjectEqualWithNPE() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            IComparator<Car> objectComparator = createCarComparator();

            Car carBmvI3 = createBmvI3();
            Car carBmvX5M = createBmwX5M();
            carBmvX5M.setTransmission(null);
            carBmvX5M.setEngine(null);

            Assertions.assertFalse(objectComparator.equal(carBmvI3, carBmvX5M));
        });
    }

    @Test
    public void testObjectEqualWithSafeNPE() {
        IComparator<Car> objectComparator = createSafeCarComparator();

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

    private IComparator<Car> createCarComparator() {
        return new ObjectComparatorBuilder<Car>()
                .putFieldComparator("carBrand", FieldComparator.simpleFieldComparator(Car::getCarBrand))
                .putFieldComparator("model", FieldComparator.simpleFieldComparator(Car::getModel))
                .putFieldComparator("color", FieldComparator.simpleFieldComparator(Car::getColor))
                .putFieldComparator("doorCount", FieldComparator.simpleFieldComparator(Car::getDoorCount))
                .putFieldComparator("engine", new SubObjectComparatorBuilder<Car, Engine>()
                        .setExtractor(Car::getEngine)
                        .setObjectComparator(new ObjectComparatorBuilder<Engine>()
                                .putFieldComparator("horsePower", FieldComparator.simpleFieldComparator(Engine::getHorsePower))
                                .putFieldComparator("volume", FieldComparator.simpleFieldComparator(Engine::getVolume))
                                .putFieldComparator("countCylinder", FieldComparator.simpleFieldComparator(Engine::getCountCylinder))
                                .build())
                        .build())
                .putFieldComparator("transmission", new SubObjectComparatorBuilder<Car, Transmission>()
                        .setExtractor(Car::getTransmission)
                        .setObjectComparator(new ObjectComparatorBuilder<Transmission>()
                                .putFieldComparator("countSteps", FieldComparator.simpleFieldComparator(Transmission::getCountSteps))
                                .putFieldComparator("transmissionType", FieldComparator.simpleFieldComparator(Transmission::getTransmissionType))
                                .build())
                        .build())
                .build();
    }

    private IComparator<Car> createSafeCarComparator() {
        return new ObjectComparatorBuilder<Car>()
                // Option with builder
                .putFieldComparator("carBrand", new FieldSafeComparatorBuilder<Car, String>()
                        .setExtractor(Car::getCarBrand)
                        .setComparator(Objects::equals)
                        .build())
                // Option without builder
                .putFieldComparator("model", FieldComparator.simpleFieldComparator(new NPESafeFunctionWrapper<>(Car::getModel)))
                .putFieldComparator("color", FieldComparator.simpleFieldComparator(new NPESafeFunctionWrapper<>(Car::getColor)))
                .putFieldComparator("doorCount", FieldComparator.simpleFieldComparator(new NPESafeFunctionWrapper<>(Car::getDoorCount)))
                // Option with builder
                .putFieldComparator("engine", new SubObjectSafeComparatorBuilder<Car, Engine>()
                        .setExtractor(Car::getEngine) // builder automatically wrapped in safe method
                        .setObjectComparator(new ObjectComparatorBuilder<Engine>()
                                .putFieldComparator("horsePower", FieldComparator.simpleFieldComparator(new NPESafeFunctionWrapper<>(Engine::getHorsePower)))
                                .putFieldComparator("volume", FieldComparator.simpleFieldComparator(new NPESafeFunctionWrapper<>(Engine::getVolume)))
                                .putFieldComparator("countCylinder", FieldComparator.simpleFieldComparator(new NPESafeFunctionWrapper<>(Engine::getCountCylinder)))
                                .build())
                        .build())
                // Option without builder
                .putFieldComparator("transmission", new SubObjectComparatorBuilder<Car, Transmission>()
                        .setExtractor(new NPESafeFunctionWrapper<>(Car::getTransmission)) // manual wrapping in safe method
                        .setObjectComparator(new ObjectComparatorBuilder<Transmission>()
                                .putFieldComparator("countSteps", FieldComparator.simpleFieldComparator(new NPESafeFunctionWrapper<>(Transmission::getCountSteps)))
                                .putFieldComparator("transmissionType", FieldComparator.simpleFieldComparator(new NPESafeFunctionWrapper<>(Transmission::getTransmissionType)))
                                .build())
                        .build())
                .build();
    }

}
