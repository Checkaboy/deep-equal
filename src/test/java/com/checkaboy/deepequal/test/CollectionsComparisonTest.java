package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.comparator.model.CollectionComparator;
import com.checkaboy.deepequal.comparator.model.FieldComparator;
import com.checkaboy.deepequal.comparator.model.ObjectComparator;
import com.checkaboy.deepequal.comparator.model.interf.ICollectionComparator;
import com.checkaboy.deepequal.comparator.model.interf.IObjectComparator;
import com.checkaboy.deepequal.model.pet.EAnimal;
import com.checkaboy.deepequal.model.pet.Pet;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class CollectionsComparisonTest {

    private ICollectionComparator<List<Pet>, Pet, List<Pet>, Pet> createComparator() {
        IObjectComparator<Pet, Pet> petComparator = new ObjectComparator<>();
        petComparator.put("nickname", FieldComparator.simpleFieldComparator(Pet::getNickname));
        petComparator.put("age", FieldComparator.simpleFieldComparator(Pet::getAge));
        petComparator.put("animal", FieldComparator.simpleFieldComparator(Pet::getAnimal));
        return new CollectionComparator<>(
                petComparator
        );
    }

    @Test
    public void withoutOrderOfSuccession() {
        List<Pet> pets1 = createFirstList();
        List<Pet> pets2 = createSecondList();
        ICollectionComparator<List<Pet>, Pet, List<Pet>, Pet> comparator = createComparator();
        Assert.assertTrue(comparator.compare(null, pets1, pets2));
    }

    private List<Pet> createFirstList() {
        List<Pet> list = new ArrayList<>();

        {
            Pet catSimon = new Pet();
            catSimon.setNickname("Simon");
            catSimon.setAge(2);
            catSimon.setAnimal(EAnimal.CAT);
            list.add(catSimon);
        }

        {
            Pet dogRex = new Pet();
            dogRex.setNickname("Rex");
            dogRex.setAge(10);
            dogRex.setAnimal(EAnimal.DOG);
            list.add(dogRex);
        }

        {
            Pet catFluffy = new Pet();
            catFluffy.setNickname("Fluffy");
            catFluffy.setAge(7);
            catFluffy.setAnimal(EAnimal.CAT);
            list.add(catFluffy);
        }

        return list;
    }

    private List<Pet> createSecondList() {
        List<Pet> list = new ArrayList<>();

        {
            Pet catFluffy = new Pet();
            catFluffy.setNickname("Fluffy");
            catFluffy.setAge(7);
            catFluffy.setAnimal(EAnimal.CAT);
            list.add(catFluffy);
        }

        {
            Pet catSimon = new Pet();
            catSimon.setNickname("Simon");
            catSimon.setAge(2);
            catSimon.setAnimal(EAnimal.CAT);
            list.add(catSimon);
        }

        {
            Pet dogRex = new Pet();
            dogRex.setNickname("Rex");
            dogRex.setAge(10);
            dogRex.setAnimal(EAnimal.DOG);
            list.add(dogRex);
        }

        return list;
    }

    @Test
    public void withoutOrderOfSuccessionAndWithDuplicate() {
        List<Pet> pets1 = createThirdList();
        List<Pet> pets2 = createFourthList();
        ICollectionComparator<List<Pet>, Pet, List<Pet>, Pet> comparator = createComparator();
        Assert.assertFalse(comparator.compare(null, pets1, pets2));
    }

    private List<Pet> createThirdList() {
        List<Pet> list = new ArrayList<>();

        {
            Pet catFluffy = new Pet();
            catFluffy.setNickname("Fluffy");
            catFluffy.setAge(7);
            catFluffy.setAnimal(EAnimal.CAT);
            list.add(catFluffy);
        }

        {
            Pet catSimon = new Pet();
            catSimon.setNickname("Simon");
            catSimon.setAge(2);
            catSimon.setAnimal(EAnimal.CAT);
            list.add(catSimon);
        }

        {
            Pet dogRex = new Pet();
            dogRex.setNickname("Rex");
            dogRex.setAge(10);
            dogRex.setAnimal(EAnimal.DOG);
            list.add(dogRex);
        }

        {
            Pet dogRex = new Pet();
            dogRex.setNickname("Rex");
            dogRex.setAge(10);
            dogRex.setAnimal(EAnimal.DOG);
            list.add(dogRex);
        }

        return list;
    }

    private List<Pet> createFourthList() {
        List<Pet> list = new ArrayList<>();

        {
            Pet catFluffy = new Pet();
            catFluffy.setNickname("Fluffy");
            catFluffy.setAge(7);
            catFluffy.setAnimal(EAnimal.CAT);
            list.add(catFluffy);
        }

        {
            Pet catSimon = new Pet();
            catSimon.setNickname("Simon");
            catSimon.setAge(2);
            catSimon.setAnimal(EAnimal.CAT);
            list.add(catSimon);
        }

        {
            Pet catSimon = new Pet();
            catSimon.setNickname("Simon");
            catSimon.setAge(2);
            catSimon.setAnimal(EAnimal.CAT);
            list.add(catSimon);
        }

        {
            Pet dogRex = new Pet();
            dogRex.setNickname("Rex");
            dogRex.setAge(10);
            dogRex.setAnimal(EAnimal.DOG);
            list.add(dogRex);
        }

        return list;
    }

    @Test
    public void leftUnJoin() {
        List<Pet> pets1 = createFivedList();
        List<Pet> pets2 = createSixedList();
//        ICollectionComparator<List<Pet>, Pet> comparator = createComparator();
//        System.out.println(comparator.objectsNotContainsInSecondCollection(pets1, pets2));
    }

    @Test
    public void leftUnJoinWithDuplicate() {
        List<Pet> pets1 = createFivedList();
        List<Pet> pets2 = createFourthList();
//        ICollectionComparator<List<Pet>, Pet> comparator = createComparator();
//        System.out.println(comparator.objectsNotContainsInSecondCollection(pets1, pets2));
    }

    private List<Pet> createFivedList() {
        List<Pet> list = new ArrayList<>();

        {
            Pet catFluffy = new Pet();
            catFluffy.setNickname("Fluffy");
            catFluffy.setAge(7);
            catFluffy.setAnimal(EAnimal.CAT);
            list.add(catFluffy);
        }

        {
            Pet catSimon = new Pet();
            catSimon.setNickname("Simon");
            catSimon.setAge(2);
            catSimon.setAnimal(EAnimal.CAT);
            list.add(catSimon);
        }

        {
            Pet dogRex = new Pet();
            dogRex.setNickname("Rex");
            dogRex.setAge(10);
            dogRex.setAnimal(EAnimal.DOG);
            list.add(dogRex);
        }

        {
            Pet dogMax = new Pet();
            dogMax.setNickname("Max");
            dogMax.setAge(12);
            dogMax.setAnimal(EAnimal.DOG);
            list.add(dogMax);
        }

        return list;
    }

    private List<Pet> createSixedList() {
        List<Pet> list = new ArrayList<>();

        {
            Pet catFluffy = new Pet();
            catFluffy.setNickname("Fluffy");
            catFluffy.setAge(7);
            catFluffy.setAnimal(EAnimal.CAT);
            list.add(catFluffy);
        }

        {
            Pet catSimon = new Pet();
            catSimon.setNickname("Simon");
            catSimon.setAge(2);
            catSimon.setAnimal(EAnimal.CAT);
            list.add(catSimon);
        }

        {
            Pet dogRex = new Pet();
            dogRex.setNickname("Rex");
            dogRex.setAge(10);
            dogRex.setAnimal(EAnimal.DOG);
            list.add(dogRex);
        }

        return list;
    }

}
