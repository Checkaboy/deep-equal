package com.checkaboy.deepequal.comparator.array.builder;

import com.checkaboy.deepequal.IBuilder;
import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.array.strategy.IArrayComparisonStrategy;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;

/**
 * @author Taras Shaptala
 */
public interface IArrayComparatorBuilder<S, T>
        extends IBuilder<IComparator<S[], T[]>> {

    IArrayComparatorBuilder<S, T> setStrategy(IArrayComparisonStrategy<S, T> strategy);

    IArrayComparatorBuilder<S, T> setComparator(IFieldComparator<S, T> comparator);

}
