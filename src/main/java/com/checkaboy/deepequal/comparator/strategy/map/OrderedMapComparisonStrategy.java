package com.checkaboy.deepequal.comparator.strategy.map;

import com.checkaboy.deepequal.comparator.strategy.collection.OrderedCollectionComparisonStrategy;

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
