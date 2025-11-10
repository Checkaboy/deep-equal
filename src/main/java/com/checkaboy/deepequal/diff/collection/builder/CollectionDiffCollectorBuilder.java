package com.checkaboy.deepequal.diff.collection.builder;

import com.checkaboy.deepequal.diff.collection.CollectionDiffCollector;
import com.checkaboy.deepequal.diff.collection.ICollectionDiffCollector;
import com.checkaboy.deepequal.diff.collection.strategy.ICollectionDiffCollectionStrategy;
import com.checkaboy.deepequal.diff.collection.strategy.OrderedCollectionDiffCollectionStrategy;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class CollectionDiffCollectorBuilder<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends AbstractBiTypifiedContainer<SV, TV>
        implements ICollectionDiffCollectorBuilder<SC, SV, TC, TV> {

    private ICollectionDiffCollectionStrategy<SC, SV, TC, TV> strategy = new OrderedCollectionDiffCollectionStrategy<>(
            ArrayList::new,
            (comparisonContext, source, target) -> Objects.equals(source, target)
    );

    protected CollectionDiffCollectorBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public CollectionDiffCollectorBuilder<SC, SV, TC, TV> setStrategy(ICollectionDiffCollectionStrategy<SC, SV, TC, TV> strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public ICollectionDiffCollector<SC, SV, TC, TV> build() {
        return new CollectionDiffCollector<>(strategy);
    }

}
