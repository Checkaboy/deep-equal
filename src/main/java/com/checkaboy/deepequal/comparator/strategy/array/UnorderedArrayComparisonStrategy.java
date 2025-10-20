package com.checkaboy.deepequal.comparator.strategy.array;

import com.checkaboy.deepequal.comparator.strategy.collection.UnorderedCollectionComparisonStrategy;

/**
 * @author Taras Shaptala
 */
public class UnorderedArrayComparisonStrategy<S, T>
        extends ArrayAsCollectionComparisonStrategyAdapter<S, T>
        implements IArrayComparisonStrategy<S, T> {

    public UnorderedArrayComparisonStrategy() {
        super(new UnorderedCollectionComparisonStrategy<>());
    }

}
