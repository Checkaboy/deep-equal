package com.checkaboy.deepequal.comparator.builder;

import com.checkaboy.deepequal.comparator.builder.interf.ICollectionComparatorBuilder;
import com.checkaboy.deepequal.comparator.model.CollectionComparator;
import com.checkaboy.deepequal.comparator.model.interf.ICollectionComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.strategy.collection.ICollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.strategy.collection.UnorderedCollectionComparisonStrategy;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.List;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class ListComparatorBuilder<SV, TV>
        extends AbstractTypifiedContainer<TV>
        implements ICollectionComparatorBuilder<List<SV>, SV, List<TV>, TV> {

    private ICollectionComparisonStrategy<List<SV>, SV, List<TV>, TV> strategy = new UnorderedCollectionComparisonStrategy<>();
    private IFieldComparator<SV, TV> comparator = (comparisonContext, source, target) -> Objects.equals(source, target);

    public ListComparatorBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(targetType);
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

    public ICollectionComparator<List<SV>, SV, List<TV>, TV> build() {
        return new CollectionComparator<>(strategy, comparator);
    }

}
