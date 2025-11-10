package com.checkaboy.deepequal.comparator.map.strategy;

import com.checkaboy.deepequal.comparator.collection.strategy.UnorderedCollectionComparisonStrategy;

import java.util.Collection;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class UnorderedMapComparisonStrategy<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends MapAsCollectionComparisonStrategyAdapter<SM, SK, SV, TM, TK, TV>
        implements IMapComparisonStrategy<SM, SK, SV, TM, TK, TV> {

    public UnorderedMapComparisonStrategy(UnorderedCollectionComparisonStrategy<Collection<SV>, SV, Collection<TV>, TV> strategy) {
        super(strategy);
    }

    public UnorderedMapComparisonStrategy() {
        this(new UnorderedCollectionComparisonStrategy<>());
    }

}
