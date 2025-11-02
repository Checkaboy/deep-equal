package com.checkaboy.deepequal.comparator.collection.builder;

import com.checkaboy.deepequal.comparator.collection.CollectionComparator;
import com.checkaboy.deepequal.comparator.collection.ICollectionComparator;
import com.checkaboy.deepequal.comparator.collection.strategy.ICollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.collection.strategy.UnorderedCollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.List;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class ListComparatorBuilder<SV, TV>
        extends AbstractBiTypifiedContainer<SV, TV>
        implements ICollectionComparatorBuilder<List<SV>, SV, List<TV>, TV> {

    private ICollectionComparisonStrategy<List<SV>, SV, List<TV>, TV> strategy = new UnorderedCollectionComparisonStrategy<>();
    private IFieldComparator<SV, TV> comparator = (comparisonContext, source, target) -> Objects.equals(source, target);

    public ListComparatorBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public ICollectionComparatorBuilder<List<SV>, SV, List<TV>, TV> setStrategy(ICollectionComparisonStrategy<List<SV>, SV, List<TV>, TV> strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public ListComparatorBuilder<SV, TV> setComparator(IFieldComparator<SV, TV> comparator) {
        this.comparator = comparator;
        return this;
    }

    @Override
    public ICollectionComparator<List<SV>, SV, List<TV>, TV> build() {
        return new CollectionComparator<>(strategy, comparator);
    }

}
