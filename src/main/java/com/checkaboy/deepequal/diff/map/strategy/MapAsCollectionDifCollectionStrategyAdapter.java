package com.checkaboy.deepequal.diff.map.strategy;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.collection.strategy.ICollectionDiffCollectionStrategy;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;

import java.util.Collection;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapAsCollectionDifCollectionStrategyAdapter<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        implements IMapDiffCollectionStrategy<SM, SK, SV, TM, TK, TV> {

    private final ICollectionDiffCollectionStrategy<Collection<SV>, SV, Collection<TV>, TV> collectorStrategy;

    public MapAsCollectionDifCollectionStrategyAdapter(ICollectionDiffCollectionStrategy<Collection<SV>, SV, Collection<TV>, TV> collectorStrategy) {
        this.collectorStrategy = collectorStrategy;
    }

    @Override
    public Collection<IDiffNode> collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SM source, TM target, String currentPath) {
        return collectorStrategy.collect(comparisonContext, diffNodeFactory, source.values(), target.values(), currentPath);
    }

}
