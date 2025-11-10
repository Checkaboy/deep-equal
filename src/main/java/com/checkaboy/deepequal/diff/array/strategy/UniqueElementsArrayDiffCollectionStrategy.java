package com.checkaboy.deepequal.diff.array.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.diff.collection.strategy.UniqueElementsCollectionDiffCollectionStrategy;
import com.checkaboy.deepequal.diff.container.IDiffNode;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class UniqueElementsArrayDiffCollectionStrategy<S, T>
        extends ArrayAsCollectionDiffCollectionStrategyAdapter<S, T>
        implements IArrayDiffCollectionStrategy<S, T> {

    public UniqueElementsArrayDiffCollectionStrategy(UniqueElementsCollectionDiffCollectionStrategy<Collection<S>, S, Collection<T>, T> collectorStrategy) {
        super(collectorStrategy);
    }

    protected UniqueElementsArrayDiffCollectionStrategy(Supplier<Collection<IDiffNode>> constructor, IFieldComparator<S, T> comparator) {
        this(new UniqueElementsCollectionDiffCollectionStrategy<>(constructor, comparator));
    }

}
