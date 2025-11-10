package com.checkaboy.deepequal.diff.map;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.map.strategy.IMapDiffCollectionStrategy;

import java.util.Collection;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapDiffCollector<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        implements IMapDiffCollector<SM, SK, SV, TM, TK, TV> {

    private final IMapDiffCollectionStrategy<SM, SK, SV, TM, TK, TV> strategy;

    public MapDiffCollector(IMapDiffCollectionStrategy<SM, SK, SV, TM, TK, TV> strategy) {
        this.strategy = strategy;
    }

    @Override
    public IDiffNode collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SM source, TM target, String currentPath) {
        if (source == null && target == null) return null;

        IDiffNode node = diffNodeFactory.create(currentPath, source, target);

        if (source == null || target == null) return node;

        Collection<IDiffNode> diffs = strategy.collect(comparisonContext, diffNodeFactory, source, target, currentPath);

        if (diffs.isEmpty()) return null;
        else {
            node.addChildren(diffs);
            return node;
        }
    }

}
