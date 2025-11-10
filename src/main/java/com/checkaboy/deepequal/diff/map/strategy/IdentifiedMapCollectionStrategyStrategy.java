package com.checkaboy.deepequal.diff.map.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class IdentifiedMapCollectionStrategyStrategy<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        implements IMapDiffCollectionStrategy<SM, SK, SV, TM, TK, TV> {

    private final Supplier<Collection<IDiffNode>> constructor;
    private final IFieldComparator<SV, TV> comparator;
    private final Function<SK, TK> keyCaster;

    public IdentifiedMapCollectionStrategyStrategy(Supplier<Collection<IDiffNode>> constructor, IFieldComparator<SV, TV> comparator, Function<SK, TK> keyCaster) {
        this.constructor = constructor;
        this.comparator = comparator;
        this.keyCaster = keyCaster;
    }

    @Override
    public Collection<IDiffNode> collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SM source, TM target, String currentPath) {
        Collection<IDiffNode> collection = constructor.get();

        for (Map.Entry<SK, SV> entry : source.entrySet()) {
            SV sourceValue = entry.getValue();
            SK sourceKey = entry.getKey();
            TV targetValue = target.get(keyCaster.apply(sourceKey));
            if (!comparator.compare(comparisonContext, sourceValue, targetValue))
                collection.add(diffNodeFactory.create(currentPath + "[" + sourceKey + "]", source, targetValue));
        }

        return collection.isEmpty() ? null : collection;
    }

}
