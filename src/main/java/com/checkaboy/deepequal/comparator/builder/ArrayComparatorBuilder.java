package com.checkaboy.deepequal.comparator.builder;

import com.checkaboy.deepequal.comparator.builder.interf.IArrayComparatorBuilder;
import com.checkaboy.deepequal.comparator.model.ArrayComparator;
import com.checkaboy.deepequal.comparator.model.interf.IArrayComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.strategy.array.ArrayComparisonStrategyAdapter;
import com.checkaboy.deepequal.comparator.strategy.array.IArrayComparisonStrategy;
import com.checkaboy.deepequal.comparator.strategy.collection.ICollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.strategy.collection.UnorderedCollectionComparisonStrategy;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class ArrayComparatorBuilder<S, T>
        implements IArrayComparatorBuilder<S, T> {

    private IArrayComparisonStrategy<S, T> strategy = new ArrayComparisonStrategyAdapter<>(new UnorderedCollectionComparisonStrategy<>());
    private IFieldComparator<S, T> comparator = (comparisonContext, source, target) -> Objects.equals(source, target);

    @Override
    public ArrayComparatorBuilder<S, T> setStrategy(ICollectionComparisonStrategy<Collection<S>, S, Collection<T>, T> strategy) {
        this.strategy = new ArrayComparisonStrategyAdapter<>(strategy);
        return this;
    }

    @Override
    public ArrayComparatorBuilder<S, T> setStrategy(IArrayComparisonStrategy<S, T> strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public ArrayComparatorBuilder<S, T> setComparator(IFieldComparator<S, T> comparator) {
        this.comparator = comparator;
        return this;
    }

    public IArrayComparator<S, T> build() {
        return new ArrayComparator<>(strategy, comparator);
    }

}
