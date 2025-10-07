package com.checkaboy.deepequal.comparator.builder;

import com.checkaboy.deepequal.comparator.builder.interf.ICollectionComparatorBuilder;
import com.checkaboy.deepequal.comparator.model.CollectionComparator;
import com.checkaboy.deepequal.comparator.model.interf.ICollectionComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.strategy.collection.ICollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.strategy.collection.UnorderedCollectionComparisonStrategy;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.Objects;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public class SetComparatorBuilder<SV, TV>
        extends AbstractTypifiedContainer<TV>
        implements ICollectionComparatorBuilder<Set<SV>, SV, Set<TV>, TV> {

    private ICollectionComparisonStrategy<Set<SV>, SV, Set<TV>, TV> strategy = new UnorderedCollectionComparisonStrategy<>();
    private IFieldComparator<SV, TV> comparator = (comparisonContext, source, target) -> Objects.equals(source, target);

    public SetComparatorBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(targetType);
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

    public ICollectionComparator<Set<SV>, SV, Set<TV>, TV> build() {
        return new CollectionComparator<>(strategy, comparator);
    }

}
