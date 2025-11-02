package com.checkaboy.deepequal.comparator.collection.builder;

import com.checkaboy.deepequal.comparator.collection.CollectionComparator;
import com.checkaboy.deepequal.comparator.collection.ICollectionComparator;
import com.checkaboy.deepequal.comparator.collection.strategy.ICollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.collection.strategy.UnorderedCollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.Objects;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public class SetComparatorBuilder<SV, TV>
        extends AbstractBiTypifiedContainer<SV, TV>
        implements ICollectionComparatorBuilder<Set<SV>, SV, Set<TV>, TV> {

    private ICollectionComparisonStrategy<Set<SV>, SV, Set<TV>, TV> strategy = new UnorderedCollectionComparisonStrategy<>();
    private IFieldComparator<SV, TV> comparator = (comparisonContext, source, target) -> Objects.equals(source, target);

    public SetComparatorBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public ICollectionComparatorBuilder<Set<SV>, SV, Set<TV>, TV> setStrategy(ICollectionComparisonStrategy<Set<SV>, SV, Set<TV>, TV> strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public SetComparatorBuilder<SV, TV> setComparator(IFieldComparator<SV, TV> comparator) {
        this.comparator = comparator;
        return this;
    }

    @Override
    public ICollectionComparator<Set<SV>, SV, Set<TV>, TV> build() {
        return new CollectionComparator<>(strategy, comparator);
    }

}
