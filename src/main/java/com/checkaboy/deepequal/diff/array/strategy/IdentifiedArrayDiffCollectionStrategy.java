package com.checkaboy.deepequal.diff.array.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.diff.collection.strategy.IdentifiedCollectionDiffCollectionStrategy;
import com.checkaboy.deepequal.diff.container.IDiffNode;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class IdentifiedArrayDiffCollectionStrategy<S, T>
        extends ArrayAsCollectionDiffCollectionStrategyAdapter<S, T>
        implements IArrayDiffCollectionStrategy<S, T> {

    public IdentifiedArrayDiffCollectionStrategy(IdentifiedCollectionDiffCollectionStrategy<Collection<S>, S, Collection<T>, T> strategy) {
        super(strategy);
    }

    public IdentifiedArrayDiffCollectionStrategy(Supplier<Collection<IDiffNode>> constructor, IFieldComparator<S, T> comparator, IFieldComparator<S, T> idComparator) {
        this(new IdentifiedCollectionDiffCollectionStrategy<>(constructor, comparator, idComparator));
    }

}
