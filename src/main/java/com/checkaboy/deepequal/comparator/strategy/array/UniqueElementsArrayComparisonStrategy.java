package com.checkaboy.deepequal.comparator.strategy.array;

import com.checkaboy.deepequal.comparator.strategy.collection.UniqueElementsCollectionComparisonStrategy;

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
