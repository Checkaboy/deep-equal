package com.checkaboy.deepequal.diff.model;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.model.interf.ICollectionDiffCollector;
import com.checkaboy.deepequal.diff.strategy.collection.ICollectionDiffCollectorStrategy;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class CollectionDiffCollector<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionDiffCollector<SC, SV, TC, TV> {

    private final ICollectionDiffCollectorStrategy<SC, SV, TC, TV> strategy;

    public CollectionDiffCollector(ICollectionDiffCollectorStrategy<SC, SV, TC, TV> strategy) {
        this.strategy = strategy;
    }

    @Override
    public IDiffNode collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SC source, TC target, String currentPath) {
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
