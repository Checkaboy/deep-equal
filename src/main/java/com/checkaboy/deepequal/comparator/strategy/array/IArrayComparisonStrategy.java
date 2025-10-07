package com.checkaboy.deepequal.comparator.strategy.array;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

/**
 * @author Taras Shaptala
 */
public interface IArrayComparisonStrategy<S, T> {

    boolean compareArrays(IComparisonContext comparisonContext, S[] source, T[] target, IFieldComparator<S, T> comparator);

}
