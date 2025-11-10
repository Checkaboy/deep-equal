package com.checkaboy.deepequal.diff.map.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.diff.collection.strategy.UnorderedCollectionDiffCollectionStrategy;
import com.checkaboy.deepequal.diff.container.IDiffNode;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class UnorderedMapCollectionStrategyStrategy<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends MapAsCollectionDifCollectionStrategyAdapter<SM, SK, SV, TM, TK, TV>
        implements IMapDiffCollectionStrategy<SM, SK, SV, TM, TK, TV> {

    public UnorderedMapCollectionStrategyStrategy(UnorderedCollectionDiffCollectionStrategy<Collection<SV>, SV, Collection<TV>, TV> collectorStrategy) {
        super(collectorStrategy);
    }

    public UnorderedMapCollectionStrategyStrategy(Supplier<Collection<IDiffNode>> constructor, IFieldComparator<SV, TV> comparator) {
        this(new UnorderedCollectionDiffCollectionStrategy<>(constructor, comparator));
    }

}
