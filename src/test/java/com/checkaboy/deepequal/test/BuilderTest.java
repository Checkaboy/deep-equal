package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.comparator.field.builder.FieldComparatorBuilder;
import com.checkaboy.deepequal.comparator.object.builder.ObjectComparatorBuilder;
import com.checkaboy.deepequal.comparator.field.FieldComparator;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.comparator.object.IObjectComparator;
import com.checkaboy.deepequal.model.car.Car;
import com.checkaboy.deepequal.model.car.ETransmissionType;
import com.checkaboy.deepequal.model.car.Engine;
import com.checkaboy.deepequal.model.car.Transmission;
import org.junit.Assert;
import org.junit.Test;


import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class BuilderTest {

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

        FieldComparator<Car, String, Car, String> fieldComparator = new FieldComparator<>(Car::getCarBrand, Car::getCarBrand, (comparisonContext, source, target) -> Objects.equals(source, target));
        Assert.assertTrue(fieldComparator.compare(null, carBmvI3, carBmvI8));
        Assert.assertFalse(fieldComparator.compare(null, carBmvI3, carMercedesAMG63));
    }

    @Test
    public void testObjectEqual() {
        IFieldComparator<Car, Car> objectComparator = createCarComparator();

        Car carBmvI3 = createBmvI3();
        Car carBmvX5M = createBmwX5M();

        Assert.assertFalse(objectComparator.compare(null, carBmvI3, carBmvX5M));
    }

    @Test
    public void testObjectEqualWithNPE() {
        IFieldComparator<Car, Car> objectComparator = createCarComparator();

        Car carBmvI3 = createBmvI3();
        Car carBmvX5M = createBmwX5M();
        carBmvX5M.setTransmission(null);
        carBmvX5M.setEngine(null);

        Assert.assertFalse(objectComparator.compare(null, carBmvI3, carBmvX5M));
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

    private IObjectComparator<Car, Car> createCarComparator() {
        ObjectComparatorBuilder<Car, Car> comparatorBuilder = new ObjectComparatorBuilder<>(Car.class, Car.class)
                .putFieldComparator("carBrand", FieldComparatorBuilder.oneObjectFieldComparator(Car::getCarBrand))
                .putFieldComparator("model", FieldComparatorBuilder.oneObjectFieldComparator(Car::getModel))
                .putFieldComparator("color", FieldComparatorBuilder.oneObjectFieldComparator(Car::getColor))
                .putFieldComparator("doorCount", FieldComparatorBuilder.oneObjectFieldComparator(Car::getDoorCount))
                .putFieldComparator("engine",
                        new FieldComparatorBuilder<Car, Engine, Car, Engine>(Engine.class, Engine.class)
                                .setTargetExtractor(Car::getEngine)
                                .setSourceExtractor(Car::getEngine)
                                .setComparator(
                                        new ObjectComparatorBuilder<>(Engine.class, Engine.class)
                                                .putFieldComparator("horsePower", FieldComparatorBuilder.oneObjectFieldComparator(Engine::getHorsePower))
                                                .putFieldComparator("volume", FieldComparatorBuilder.oneObjectFieldComparator(Engine::getVolume))
                                                .putFieldComparator("countCylinder", FieldComparatorBuilder.oneObjectFieldComparator(Engine::getCountCylinder))
                                                .build())
                                .build())
                .putFieldComparator("transmission",
                        new FieldComparatorBuilder<Car, Transmission, Car, Transmission>(Transmission.class, Transmission.class)
                                .setSourceExtractor(Car::getTransmission)
                                .setTargetExtractor(Car::getTransmission)
                                .setComparator(
                                        new ObjectComparatorBuilder<>(Transmission.class, Transmission.class)
                                                .putFieldComparator("countSteps", FieldComparatorBuilder.oneObjectFieldComparator(Transmission::getCountSteps))
                                                .putFieldComparator("transmissionType", FieldComparatorBuilder.oneObjectFieldComparator(Transmission::getTransmissionType))
                                                .build())
                                .build());

        return comparatorBuilder.build();
    }

}
