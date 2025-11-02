package com.checkaboy.deepequal.comparator.array.strategy;

import com.checkaboy.deepequal.comparator.collection.strategy.UniqueElementsCollectionComparisonStrategy;

/**
 * @author Taras Shaptala
 */
public class UniqueElementsArrayComparisonStrategy<S, T>
        extends ArrayAsCollectionComparisonStrategyAdapter<S, T>
        implements IArrayComparisonStrategy<S, T> {

    public UniqueElementsArrayComparisonStrategy() {
        super(new UniqueElementsCollectionComparisonStrategy<>());
    }
}
