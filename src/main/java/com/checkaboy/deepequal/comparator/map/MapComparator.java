package com.checkaboy.deepequal.comparator.map;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.comparator.map.strategy.IMapComparisonStrategy;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapComparator<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        implements IMapComparator<SM, SK, SV, TM, TK, TV> {

    private final IMapComparisonStrategy<SM, SK, SV, TM, TK, TV> strategy;
    private final IFieldComparator<SV, TV> comparator;

    public MapComparator(IMapComparisonStrategy<SM, SK, SV, TM, TK, TV> strategy, IFieldComparator<SV, TV> comparator) {
        this.strategy = strategy;
        this.comparator = comparator;
    }

    @Override
    public boolean compare(IComparisonContext comparisonContext, SM source, TM target) {
        if (source == null && target == null) return true;
        if (source == null || target == null) return false;
        if (source.size() != target.size()) return false;

        return strategy.compare(comparisonContext, source, target, comparator);
    }

}
