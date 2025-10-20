package com.checkaboy.deepequal.comparator.strategy.array;

import com.checkaboy.deepequal.comparator.strategy.collection.OrderedCollectionComparisonStrategy;

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
