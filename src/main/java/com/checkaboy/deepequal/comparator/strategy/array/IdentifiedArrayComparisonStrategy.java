package com.checkaboy.deepequal.comparator.strategy.array;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.strategy.collection.IdentifiedCollectionComparisonStrategy;

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
