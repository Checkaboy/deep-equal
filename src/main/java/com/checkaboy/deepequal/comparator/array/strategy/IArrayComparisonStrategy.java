package com.checkaboy.deepequal.comparator.array.strategy;

import com.checkaboy.deepequal.comparator.collection.IClusterComparisonStrategy;

/**
 * @author Taras Shaptala
 */
public interface IArrayComparisonStrategy<S, T>
        extends IClusterComparisonStrategy<S[], S, T[], T> {
}
