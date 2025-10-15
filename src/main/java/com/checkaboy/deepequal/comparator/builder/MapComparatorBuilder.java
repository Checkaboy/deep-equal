package com.checkaboy.deepequal.comparator.builder;

import com.checkaboy.deepequal.comparator.builder.interf.IMapComparatorBuilder;
import com.checkaboy.deepequal.comparator.model.MapComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.model.interf.IMapComparator;
import com.checkaboy.deepequal.comparator.strategy.map.DirectKeyMatchingStrategy;
import com.checkaboy.deepequal.comparator.strategy.map.IKeyMatchingStrategy;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.Map;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class MapComparatorBuilder<SK, SV, TK, TV>
        extends AbstractBiTypifiedContainer<SV, TV>
        implements IMapComparatorBuilder<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> {

    protected IKeyMatchingStrategy<SK, SV, TK, TV> strategy = new DirectKeyMatchingStrategy<>();
    protected IFieldComparator<SV, TV> comparator = (comparisonContext, source, target) -> Objects.equals(source, target);

    public MapComparatorBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public IMapComparatorBuilder<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> setStrategy(IKeyMatchingStrategy<SK, SV, TK, TV> strategy) {
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

}
