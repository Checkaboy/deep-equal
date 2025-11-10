package com.checkaboy.deepequal.diff.array.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.diff.collection.strategy.UnorderedCollectionDiffCollectionStrategy;
import com.checkaboy.deepequal.diff.container.IDiffNode;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class UnorderedArrayDiffCollectionStrategy<S, T>
        extends ArrayAsCollectionDiffCollectionStrategyAdapter<S, T>
        implements IArrayDiffCollectionStrategy<S, T> {

    public UnorderedArrayDiffCollectionStrategy(UnorderedCollectionDiffCollectionStrategy<Collection<S>, S, Collection<T>, T> collectorStrategy) {
        super(collectorStrategy);
    }

    public UnorderedArrayDiffCollectionStrategy(Supplier<Collection<IDiffNode>> constructor, IFieldComparator<S, T> comparator) {
        this(new UnorderedCollectionDiffCollectionStrategy<>(constructor, comparator));
    }

}
