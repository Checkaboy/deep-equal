package com.checkaboy.deepequal.comparator.map.strategy;

import com.checkaboy.deepequal.comparator.collection.strategy.UniqueElementsCollectionComparisonStrategy;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class UniqueElementsMapComparisonStrategy<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends MapAsCollectionComparisonStrategyAdapter<SM, SK, SV, TM, TK, TV>
        implements IMapComparisonStrategy<SM, SK, SV, TM, TK, TV> {

    public UniqueElementsMapComparisonStrategy() {
        super(new UniqueElementsCollectionComparisonStrategy<>());
    }

}
