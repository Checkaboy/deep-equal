package com.checkaboy.deepequal.comparator.array.strategy;

import com.checkaboy.deepequal.comparator.collection.strategy.UnorderedCollectionComparisonStrategy;

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
