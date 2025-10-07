package com.checkaboy.deepequal.comparator.model;

import com.checkaboy.deepequal.comparator.model.interf.IArrayComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.strategy.array.IArrayComparisonStrategy;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

/**
 * @author Taras Shaptala
 */
public class ArrayComparator<S, T>
        implements IArrayComparator<S, T> {

    private final IArrayComparisonStrategy<S, T> strategy;
    private final IFieldComparator<S, T> comparator;

    public ArrayComparator(IArrayComparisonStrategy<S, T> strategy, IFieldComparator<S, T> comparator) {
        this.strategy = strategy;
        this.comparator = comparator;
    }

    @Override
    public boolean compare(IComparisonContext comparisonContext, S[] source, T[] target) {
        if (source == null && target == null) return true;
        if (source == null || target == null) return false;
        if (source.length != target.length) return false;

        return strategy.compareArrays(comparisonContext, source, target, comparator);
    }

}
