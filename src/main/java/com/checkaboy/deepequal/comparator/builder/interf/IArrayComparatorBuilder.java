package com.checkaboy.deepequal.comparator.builder.interf;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.strategy.array.IArrayComparisonStrategy;
import com.checkaboy.deepequal.comparator.strategy.collection.ICollectionComparisonStrategy;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface IArrayComparatorBuilder<S, T>
        extends IComparatorBuilder<S[], T[]> {

    IArrayComparatorBuilder<S, T> setStrategy(ICollectionComparisonStrategy<Collection<S>, S, Collection<T>, T> strategy);

    IArrayComparatorBuilder<S, T> setStrategy(IArrayComparisonStrategy<S, T> strategy);

    IArrayComparatorBuilder<S, T> setComparator(IFieldComparator<S, T> comparator);

}
