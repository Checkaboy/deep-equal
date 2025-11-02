package com.checkaboy.deepequal.comparator.transaction;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.context.factory.IComparisonContextFactory;

/**
 * @author Taras Shaptala
 */
public class ComparisonTransaction<S, T>
        implements IComparisonTransaction<S, T> {

    private final IComparator<S, T> comparator;
    private final IComparisonContextFactory comparisonContextFactory;

    public ComparisonTransaction(IComparator<S, T> comparator, IComparisonContextFactory comparisonContextFactory) {
        this.comparator = comparator;
        this.comparisonContextFactory = comparisonContextFactory;
    }

    @Override
    public boolean compare(S source, T target) {
        IComparisonContext comparisonContext = comparisonContextFactory.create();
        return comparator.compare(comparisonContext, source, target);
    }

}
