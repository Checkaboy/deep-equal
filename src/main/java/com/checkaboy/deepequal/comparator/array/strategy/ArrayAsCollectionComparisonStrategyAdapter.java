package com.checkaboy.deepequal.comparator.array.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.comparator.collection.strategy.ICollectionComparisonStrategy;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class ArrayAsCollectionComparisonStrategyAdapter<S, T>
        implements IArrayComparisonStrategy<S, T> {

    private final ICollectionComparisonStrategy<Collection<S>, S, Collection<T>, T> collectionStrategy;

    public ArrayAsCollectionComparisonStrategyAdapter(ICollectionComparisonStrategy<Collection<S>, S, Collection<T>, T> collectionStrategy) {
        this.collectionStrategy = collectionStrategy;
    }

    @Override
    public boolean compare(IComparisonContext comparisonContext, S[] source, T[] target, IFieldComparator<S, T> comparator) {
        List<S> sourceList = source != null ? Arrays.asList(source) : null;
        List<T> targetList = target != null ? Arrays.asList(target) : null;
        return collectionStrategy.compare(comparisonContext, sourceList, targetList, comparator);
    }

}
