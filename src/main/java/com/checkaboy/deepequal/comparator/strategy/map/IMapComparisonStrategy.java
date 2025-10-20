package com.checkaboy.deepequal.comparator.strategy.map;

import com.checkaboy.deepequal.comparator.strategy.IClusterComparisonStrategy;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapComparisonStrategy<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IClusterComparisonStrategy<SM, SV, TM, TV> {
}
