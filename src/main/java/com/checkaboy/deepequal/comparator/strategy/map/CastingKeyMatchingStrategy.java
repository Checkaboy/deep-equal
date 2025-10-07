package com.checkaboy.deepequal.comparator.strategy.map;

import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class CastingKeyMatchingStrategy<SK, SV, TK, TV>
        implements IKeyMatchingStrategy<SK, SV, TK, TV> {

    private final Function<SK, TK> keyCaster;

    public CastingKeyMatchingStrategy(Function<SK, TK> keyCaster) {
        this.keyCaster = keyCaster;
    }

    @Override
    public TV findMatchingValue(IComparisonContext comparisonContext, SK sourceKey, SV sourceValue, Map<TK, TV> target) {
        TK transformed = keyCaster.apply(sourceKey);
        return target.get(transformed);
    }

}
