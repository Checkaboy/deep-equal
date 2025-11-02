package com.checkaboy.deepequal.comparator.array.strategy;

import com.checkaboy.deepequal.comparator.collection.strategy.OrderedCollectionComparisonStrategy;

/**
 * @author Taras Shaptala
 */
public class OrderedArrayComparisonStrategy<S, T>
        extends ArrayAsCollectionComparisonStrategyAdapter<S, T>
        implements IArrayComparisonStrategy<S, T> {

    public OrderedArrayComparisonStrategy() {
        super(new OrderedCollectionComparisonStrategy<>());
    }

}
