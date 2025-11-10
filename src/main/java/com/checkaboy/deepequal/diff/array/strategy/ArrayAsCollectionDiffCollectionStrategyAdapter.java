package com.checkaboy.deepequal.diff.array.strategy;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.collection.strategy.ICollectionDiffCollectionStrategy;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class ArrayAsCollectionDiffCollectionStrategyAdapter<S, T>
        implements IArrayDiffCollectionStrategy<S, T> {

    private final ICollectionDiffCollectionStrategy<Collection<S>, S, Collection<T>, T> collectorStrategy;

    public ArrayAsCollectionDiffCollectionStrategyAdapter(ICollectionDiffCollectionStrategy<Collection<S>, S, Collection<T>, T> collectorStrategy) {
        this.collectorStrategy = collectorStrategy;
    }

    @Override
    public Collection<IDiffNode> collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, S[] source, T[] target, String currentPath) {
        List<S> sourceList = source != null ? Arrays.asList(source) : null;
        List<T> targetList = target != null ? Arrays.asList(target) : null;
        return collectorStrategy.collect(comparisonContext, diffNodeFactory, sourceList, targetList, currentPath);
    }

}
