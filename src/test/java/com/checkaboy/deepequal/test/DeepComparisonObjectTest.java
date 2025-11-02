package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.comparator.field.FieldComparator;
import com.checkaboy.deepequal.comparator.field.builder.FieldComparatorBuilder;
import com.checkaboy.deepequal.comparator.object.ObjectComparator;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.comparator.object.IObjectComparator;
import com.checkaboy.deepequal.diff.container.DiffNode;
import com.checkaboy.deepequal.diff.model.FieldDiffCollector;
import com.checkaboy.deepequal.diff.model.interf.IFieldDiffCollector;
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

        FieldComparator<Car, String, Car, String> fieldComparator = new FieldComparator<>(Car::getCarBrand, Car::getCarBrand, (comparisonContext, source, target) -> Objects.equals(source, target));
        Assert.assertTrue(fieldComparator.compare(null, carBmvI3, carBmvI8));
        Assert.assertFalse(fieldComparator.compare(null, carBmvI3, carMercedesAMG63));
    }

    @Test
    public void testPrimitiveFieldDiffCollect() {
        Car carBmvI3 = new Car();
        carBmvI3.setCarBrand("BMW");
        carBmvI3.setModel("I3");

        Car carBmvI8 = new Car();
        carBmvI8.setCarBrand("BMW");
        carBmvI8.setModel("I8");

        Car carMercedesAMG63 = new Car();
        carMercedesAMG63.setCarBrand("Mercedes");
        carMercedesAMG63.setModel("AMG 63");

        IFieldDiffCollector<Car, Car> fieldDiffCollector = FieldDiffCollector.oneObjectFieldDiffCollector("carBranch", Car::getCarBrand);
        System.out.println(fieldDiffCollector.collect(null, DiffNode::new, carBmvI3, carBmvI8, "Car"));
        System.out.println(fieldDiffCollector.collect(null, DiffNode::new, carBmvI3, carMercedesAMG63, "Car"));
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

    private IFieldComparator<Car, Car> createCarComparator() {
        IObjectComparator<Car, Car> carComparator = new ObjectComparator<>();

        carComparator.put("carBrand", FieldComparatorBuilder.oneObjectFieldComparator(Car::getCarBrand));
        carComparator.put("model", FieldComparatorBuilder.oneObjectFieldComparator(Car::getModel));
        carComparator.put("color", FieldComparatorBuilder.oneObjectFieldComparator(Car::getColor));
        carComparator.put("doorCount", FieldComparatorBuilder.oneObjectFieldComparator(Car::getDoorCount));

        {
            IObjectComparator<Engine, Engine> engineComparator = new ObjectComparator<>();
            engineComparator.put("horsePower", FieldComparatorBuilder.oneObjectFieldComparator(Engine::getHorsePower));
            engineComparator.put("volume", FieldComparatorBuilder.oneObjectFieldComparator(Engine::getVolume));
            engineComparator.put("countCylinder", FieldComparatorBuilder.oneObjectFieldComparator(Engine::getCountCylinder));
            FieldComparator<Car, Engine, Car, Engine> comparator = new FieldComparator<>(Car::getEngine, Car::getEngine, engineComparator);
            carComparator.put("engine", comparator);
        }

        {
            IObjectComparator<Transmission, Transmission> transmissionComparator = new ObjectComparator<>();
            transmissionComparator.put("countSteps", FieldComparatorBuilder.oneObjectFieldComparator(Transmission::getCountSteps));
            transmissionComparator.put("transmissionType", FieldComparatorBuilder.oneObjectFieldComparator(Transmission::getTransmissionType));
            FieldComparator<Car, Transmission, Car, Transmission> comparator = new FieldComparator<>(Car::getTransmission, Car::getTransmission, transmissionComparator);
            carComparator.put("transmissionType", comparator);
        }

        return carComparator;
    }

}
