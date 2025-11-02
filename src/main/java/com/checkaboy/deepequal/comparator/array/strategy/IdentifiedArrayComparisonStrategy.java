package com.checkaboy.deepequal.comparator.array.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.comparator.collection.strategy.IdentifiedCollectionComparisonStrategy;

/**
 * @author Taras Shaptala
 */
public class IdentifiedArrayComparisonStrategy<S, T>
        extends ArrayAsCollectionComparisonStrategyAdapter<S, T>
        implements IArrayComparisonStrategy<S, T> {

    public IdentifiedArrayComparisonStrategy(IFieldComparator<S, T> idComparator) {
        super(new IdentifiedCollectionComparisonStrategy<>(idComparator));
    }

}
