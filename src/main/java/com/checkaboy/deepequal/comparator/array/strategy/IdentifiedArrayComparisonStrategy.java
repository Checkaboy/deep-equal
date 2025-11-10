package com.checkaboy.deepequal.comparator.array.strategy;

import com.checkaboy.deepequal.comparator.collection.strategy.IdentifiedCollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class IdentifiedArrayComparisonStrategy<S, T>
        extends ArrayAsCollectionComparisonStrategyAdapter<S, T>
        implements IArrayComparisonStrategy<S, T> {

    public IdentifiedArrayComparisonStrategy(IdentifiedCollectionComparisonStrategy<Collection<S>, S, Collection<T>, T> strategy) {
        super(strategy);
    }

    public IdentifiedArrayComparisonStrategy(IFieldComparator<S, T> idComparator) {
        this(new IdentifiedCollectionComparisonStrategy<>(idComparator));
    }

}
