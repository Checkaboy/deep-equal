package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.builder.general.ObjectComparatorBuilder;
import com.checkaboy.deepequal.model.collection.CollectionComparator;
import com.checkaboy.deepequal.model.pet.EAnimal;
import com.checkaboy.deepequal.model.pet.Pet;
import com.checkaboy.deepequal.model.single.FieldComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class CollectionsComparisonTest {

    private CollectionComparator<List<Pet>, Pet> createComparator() {
        return new CollectionComparator<>(
                ArrayList::new,
                new ObjectComparatorBuilder<Pet>()
                        .putFieldComparator("nickname", FieldComparator.simpleFieldComparator(Pet::getNickname))
                        .putFieldComparator("age", FieldComparator.simpleFieldComparator(Pet::getAge))
                        .putFieldComparator("animal", FieldComparator.simpleFieldComparator(Pet::getAnimal))
                        .build()
        );
    }

    @Test
    public void withoutOrderOfSuccession() {
        List<Pet> pets1 = createFirstList();
        List<Pet> pets2 = createSecondList();
        CollectionComparator<List<Pet>, Pet> comparator = createComparator();
        Assertions.assertTrue(comparator.equal(pets1, pets2));
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
        CollectionComparator<List<Pet>, Pet> comparator = createComparator();
        Assertions.assertFalse(comparator.equal(pets1, pets2));
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
        CollectionComparator<List<Pet>, Pet> comparator = createComparator();
        System.out.println(comparator.objectsNotContainsInSecondList(pets1, pets2));
    }

    @Test
    public void leftUnJoinWithDuplicate() {
        List<Pet> pets1 = createFivedList();
        List<Pet> pets2 = createFourthList();
        CollectionComparator<List<Pet>, Pet> comparator = createComparator();
        System.out.println(comparator.objectsNotContainsInSecondList(pets1, pets2));
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
