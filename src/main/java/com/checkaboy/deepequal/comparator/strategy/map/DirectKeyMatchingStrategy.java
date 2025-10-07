package com.checkaboy.deepequal.comparator.strategy.map;

import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class DirectKeyMatchingStrategy <SK, SV, TK, TV>
        implements IKeyMatchingStrategy<SK, SV, TK, TV> {

    @Override
    public TV findMatchingValue(IComparisonContext comparisonContext, SK sourceKey, SV sourceValue, Map<TK, TV> target) {
        @SuppressWarnings("unchecked")
        TK castKey = (TK) sourceKey;
        return target.get(castKey);
    }

}
