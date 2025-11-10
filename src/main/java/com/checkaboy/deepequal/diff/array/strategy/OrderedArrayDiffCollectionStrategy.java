package com.checkaboy.deepequal.diff.array.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.diff.collection.strategy.OrderedCollectionDiffCollectionStrategy;
import com.checkaboy.deepequal.diff.container.IDiffNode;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class OrderedArrayDiffCollectionStrategy<S, T>
        extends ArrayAsCollectionDiffCollectionStrategyAdapter<S, T>
        implements IArrayDiffCollectionStrategy<S, T> {

    public OrderedArrayDiffCollectionStrategy(OrderedCollectionDiffCollectionStrategy<Collection<S>, S, Collection<T>, T> collectorStrategy) {
        super(collectorStrategy);
    }

    public OrderedArrayDiffCollectionStrategy(Supplier<Collection<IDiffNode>> constructor, IFieldComparator<S, T> comparator) {
        this(new OrderedCollectionDiffCollectionStrategy<>(constructor, comparator));
    }

}
