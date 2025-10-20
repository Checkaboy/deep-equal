package com.checkaboy.deepequal.comparator.strategy.collection;

import com.checkaboy.deepequal.comparator.strategy.IClusterComparisonStrategy;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionComparisonStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IClusterComparisonStrategy<SC, SV, TC, TV> {
}
