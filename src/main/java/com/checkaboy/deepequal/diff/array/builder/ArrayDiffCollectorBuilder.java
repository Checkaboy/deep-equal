package com.checkaboy.deepequal.diff.array.builder;

import com.checkaboy.deepequal.diff.array.ArrayDiffCollector;
import com.checkaboy.deepequal.diff.array.IArrayDiffCollector;
import com.checkaboy.deepequal.diff.array.strategy.IArrayDiffCollectionStrategy;
import com.checkaboy.deepequal.diff.array.strategy.OrderedArrayDiffCollectionStrategy;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class ArrayDiffCollectorBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IArrayDiffCollectorBuilder<S, T> {

    private IArrayDiffCollectionStrategy<S, T> strategy = new OrderedArrayDiffCollectionStrategy<>(
            ArrayList::new,
            (comparisonContext, source, target) -> Objects.equals(source, target)
    );

    protected ArrayDiffCollectorBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public ArrayDiffCollectorBuilder<S, T> setStrategy(IArrayDiffCollectionStrategy<S, T> strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public IArrayDiffCollector<S, T> build() {
        return new ArrayDiffCollector<>(strategy);
    }

}
