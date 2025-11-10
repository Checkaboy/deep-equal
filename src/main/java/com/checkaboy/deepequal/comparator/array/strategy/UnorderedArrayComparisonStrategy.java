package com.checkaboy.deepequal.comparator.array.strategy;

import com.checkaboy.deepequal.comparator.collection.strategy.UnorderedCollectionComparisonStrategy;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class UnorderedArrayComparisonStrategy<S, T>
        extends ArrayAsCollectionComparisonStrategyAdapter<S, T>
        implements IArrayComparisonStrategy<S, T> {

    public UnorderedArrayComparisonStrategy(UnorderedCollectionComparisonStrategy<Collection<S>, S, Collection<T>, T> strategy) {
        super(strategy);
    }

    public UnorderedArrayComparisonStrategy() {
        this(new UnorderedCollectionComparisonStrategy<>());
    }

}
