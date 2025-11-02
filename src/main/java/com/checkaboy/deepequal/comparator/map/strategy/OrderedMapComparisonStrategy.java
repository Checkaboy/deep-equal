package com.checkaboy.deepequal.comparator.map.strategy;

import com.checkaboy.deepequal.comparator.collection.strategy.OrderedCollectionComparisonStrategy;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class OrderedMapComparisonStrategy<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends MapAsCollectionComparisonStrategyAdapter<SM, SK, SV, TM, TK, TV>
        implements IMapComparisonStrategy<SM, SK, SV, TM, TK, TV> {

    public OrderedMapComparisonStrategy() {
        super(new OrderedCollectionComparisonStrategy<>());
    }

}
