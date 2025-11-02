package com.checkaboy.deepequal.comparator.array.builder;

import com.checkaboy.deepequal.comparator.array.ArrayComparator;
import com.checkaboy.deepequal.comparator.array.IArrayComparator;
import com.checkaboy.deepequal.comparator.array.strategy.IArrayComparisonStrategy;
import com.checkaboy.deepequal.comparator.array.strategy.UnorderedArrayComparisonStrategy;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class ArrayComparatorBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IArrayComparatorBuilder<S, T> {

    private IArrayComparisonStrategy<S, T> strategy = new UnorderedArrayComparisonStrategy<>();
    private IFieldComparator<S, T> comparator = (comparisonContext, source, target) -> Objects.equals(source, target);

    public ArrayComparatorBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
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

    @Override
    public IArrayComparator<S, T> build() {
        return new ArrayComparator<>(strategy, comparator);
    }

}
