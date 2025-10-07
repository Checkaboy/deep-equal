package com.checkaboy.deepequal.comparator.strategy.array;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.strategy.collection.ICollectionComparisonStrategy;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class ArrayComparisonStrategyAdapter<S, T>
        implements IArrayComparisonStrategy<S, T> {

    private final ICollectionComparisonStrategy<Collection<S>, S, Collection<T>, T> collectionStrategy;

    public ArrayComparisonStrategyAdapter(ICollectionComparisonStrategy<Collection<S>, S, Collection<T>, T> collectionStrategy) {
        this.collectionStrategy = collectionStrategy;
    }

    @Override
    public boolean compareArrays(IComparisonContext comparisonContext, S[] source, T[] target, IFieldComparator<S, T> comparator) {
        List<S> sList = source != null ? Arrays.asList(source) : null;
        List<T> tList = target != null ? Arrays.asList(target) : null;
        return collectionStrategy.compare(comparisonContext, sList, tList, comparator);
    }

}
