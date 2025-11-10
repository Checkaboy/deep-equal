package com.checkaboy.deepequal.comparator.array.strategy;

import com.checkaboy.deepequal.comparator.collection.strategy.OrderedCollectionComparisonStrategy;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class OrderedArrayComparisonStrategy<S, T>
        extends ArrayAsCollectionComparisonStrategyAdapter<S, T>
        implements IArrayComparisonStrategy<S, T> {

    public OrderedArrayComparisonStrategy(OrderedCollectionComparisonStrategy<Collection<S>, S, Collection<T>, T> strategy) {
        super(strategy);
    }

    public OrderedArrayComparisonStrategy() {
        this(new OrderedCollectionComparisonStrategy<>());
    }

}
