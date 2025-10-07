package com.checkaboy.deepequal.comparator.strategy.map;

import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IKeyMatchingStrategy<SK, SV, TK, TV> {

    TV findMatchingValue(IComparisonContext comparisonContext, SK sourceKey, SV sourceValue, Map<TK, TV> target);

}
