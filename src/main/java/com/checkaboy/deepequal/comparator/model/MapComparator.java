package com.checkaboy.deepequal.comparator.model;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.model.interf.IMapComparator;
import com.checkaboy.deepequal.comparator.strategy.map.IKeyMatchingStrategy;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class MapComparator<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        implements IMapComparator<SM, SK, SV, TM, TK, TV> {

    private final IKeyMatchingStrategy<SK, SV, TK, TV> keyMatchingStrategy;
    private final IFieldComparator<SV, TV> comparator;

    public MapComparator(IKeyMatchingStrategy<SK, SV, TK, TV> keyMatchingStrategy, IFieldComparator<SV, TV> comparator) {
        this.keyMatchingStrategy = keyMatchingStrategy;
        this.comparator = comparator;
    }

    @Override
    public boolean compare(IComparisonContext comparisonContext, SM source, TM target) {
        if (source == null && target == null) return true;
        if (source == null || target == null) return false;
        if (source.size() != target.size()) return false;

        for (Map.Entry<SK, SV> entry : source.entrySet()) {
            TV targetValue = keyMatchingStrategy.findMatchingValue(comparisonContext, entry.getKey(), entry.getValue(), target);
            if (!comparator.compare(comparisonContext, entry.getValue(), targetValue))
                return false;
        }

        return true;
    }

}
