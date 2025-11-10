package com.checkaboy.deepequal.comparator.array.strategy;

import com.checkaboy.deepequal.comparator.collection.strategy.UniqueElementsCollectionComparisonStrategy;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class UniqueElementsArrayComparisonStrategy<S, T>
        extends ArrayAsCollectionComparisonStrategyAdapter<S, T>
        implements IArrayComparisonStrategy<S, T> {

    public UniqueElementsArrayComparisonStrategy(UniqueElementsCollectionComparisonStrategy<Collection<S>, S, Collection<T>, T> strategy) {
        super(strategy);
    }

    public UniqueElementsArrayComparisonStrategy() {
        this(new UniqueElementsCollectionComparisonStrategy<>());
    }

}
