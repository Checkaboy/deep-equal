package com.checkaboy.deepequal.comparator.model;

import com.checkaboy.deepequal.comparator.model.interf.ICollectionComparator;
import com.checkaboy.deepequal.comparator.strategy.collection.ICollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class CollectionComparator<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionComparator<SC, SV, TC, TV> {

    protected final ICollectionComparisonStrategy<SC, SV, TC, TV> strategy;
    protected final IFieldComparator<SV, TV> comparator;

    public CollectionComparator(ICollectionComparisonStrategy<SC, SV, TC, TV> strategy, IFieldComparator<SV, TV> comparator) {
        this.strategy = strategy;
        this.comparator = comparator;
    }

    @Override
    public boolean compare(IComparisonContext comparisonContext, SC source, TC target) {
        if (source == null && target == null) return true;
        if (source == null || target == null) return false;
        if (source.size() != target.size()) return false;

        return strategy.compare(comparisonContext, source, target, comparator);
    }

}
