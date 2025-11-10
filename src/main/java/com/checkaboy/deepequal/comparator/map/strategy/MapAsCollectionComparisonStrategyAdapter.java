package com.checkaboy.deepequal.comparator.map.strategy;

import com.checkaboy.deepequal.comparator.collection.strategy.ICollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Collection;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapAsCollectionComparisonStrategyAdapter<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        implements IMapComparisonStrategy<SM, SK, SV, TM, TK, TV> {

    private final ICollectionComparisonStrategy<Collection<SV>, SV, Collection<TV>, TV> comparisonStrategy;

    public MapAsCollectionComparisonStrategyAdapter(ICollectionComparisonStrategy<Collection<SV>, SV, Collection<TV>, TV> comparisonStrategy) {
        this.comparisonStrategy = comparisonStrategy;
    }

    @Override
    public boolean compare(IComparisonContext comparisonContext, SM source, TM target, IFieldComparator<SV, TV> comparator) {
        Collection<SV> sourceCollection = source.values();
        Collection<TV> targetCollection = target.values();

        if (source.size() != target.size()) return false;

        return comparisonStrategy.compare(comparisonContext, sourceCollection, targetCollection, comparator);
    }

}
