package com.checkaboy.deepequal.diff.array;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.array.strategy.IArrayDiffCollectionStrategy;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class ArrayDiffCollector<S, T>
        implements IArrayDiffCollector<S, T> {

    private final IArrayDiffCollectionStrategy<S, T> strategy;

    public ArrayDiffCollector(IArrayDiffCollectionStrategy<S, T> strategy) {
        this.strategy = strategy;
    }

    @Override
    public IDiffNode collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, S[] source, T[] target, String currentPath) {
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
