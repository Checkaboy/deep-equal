package com.checkaboy.deepequal.model.collection;

import com.checkaboy.deepequal.model.collection.interf.ISubCollectionComparator;
import com.checkaboy.deepequal.model.collection.interf.ICollectionComparator;

import java.util.Collection;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public abstract class SubCollectionComparator<O, C extends Collection<V>, V>
        implements ISubCollectionComparator<O> {

    private final Function<O, C> extractor;
    private final ICollectionComparator<C, V> collectionComparatorStrategy;

    public SubCollectionComparator(Function<O, C> extractor, ICollectionComparator<C, V> collectionComparatorStrategy) {
        this.extractor = extractor;
        this.collectionComparatorStrategy = collectionComparatorStrategy;
    }

    @Override
    public boolean equal(O first, O second) {
        return collectionComparatorStrategy.equal(extractor.apply(first), extractor.apply(second));
    }

}
