package com.checkaboy.deepequal.diff.strategy.array;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.strategy.collection.ICollectionDiffCollectorStrategy;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class ArrayDiffCollectorStrategyAdapter<S, T>
        implements IArrayDiffCollectorStrategy<S, T> {

    private final ICollectionDiffCollectorStrategy<Collection<S>, S, Collection<T>, T> collectorStrategy;

    public ArrayDiffCollectorStrategyAdapter(ICollectionDiffCollectorStrategy<Collection<S>, S, Collection<T>, T> collectorStrategy) {
        this.collectorStrategy = collectorStrategy;
    }

    @Override
    public Collection<IDiffNode> collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, S[] source, T[] target, String currentPath) {
        List<S> sourceList = source != null ? Arrays.asList(source) : null;
        List<T> targetList = target != null ? Arrays.asList(target) : null;
        return collectorStrategy.collect(comparisonContext, diffNodeFactory, sourceList, targetList, currentPath);
    }

}
