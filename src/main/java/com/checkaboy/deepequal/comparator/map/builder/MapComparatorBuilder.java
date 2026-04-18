package com.checkaboy.deepequal.comparator.map.builder;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.comparator.map.IMapComparator;
import com.checkaboy.deepequal.comparator.map.MapComparator;
import com.checkaboy.deepequal.comparator.map.strategy.IMapComparisonStrategy;
import com.checkaboy.deepequal.comparator.map.strategy.UnorderedMapComparisonStrategy;
import com.checkaboy.objectutils.container.Abstract4TypifiedContainer;

import java.util.Map;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class MapComparatorBuilder<SK, SV, TK, TV>
        extends Abstract4TypifiedContainer<SK, SV, TK, TV>
        implements IMapComparatorBuilder<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> {

    private IMapComparisonStrategy<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> strategy = new UnorderedMapComparisonStrategy<>();
    private IFieldComparator<SV, TV> comparator = (comparisonContext, source, target) -> Objects.equals(source, target);

    protected MapComparatorBuilder(Class<SK> sourceKeyType, Class<SV> sourceValueType, Class<TK> targetKeyType, Class<TV> targetValueType) {
        super(sourceKeyType, sourceValueType, targetKeyType, targetValueType);
    }

    @Override
    public MapComparatorBuilder<SK, SV, TK, TV> setStrategy(IMapComparisonStrategy<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public MapComparatorBuilder<SK, SV, TK, TV> setComparator(IFieldComparator<SV, TV> comparator) {
        this.comparator = comparator;
        return this;
    }

    @Override
    public IMapComparator<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> build() {
        return new MapComparator<>(strategy, comparator);
    }

    public static <SK, SV, TK, TV> MapComparatorBuilder<SK, SV, TK, TV> of(
            Class<SK> sourceKeyType, Class<SV> sourceValueType, Class<TK> targetKeyType, Class<TV> targetValueType
    ) {
        return new MapComparatorBuilder<>(sourceKeyType, sourceValueType, targetKeyType, targetValueType);
    }

}
