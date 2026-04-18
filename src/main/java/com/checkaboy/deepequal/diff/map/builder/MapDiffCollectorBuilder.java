package com.checkaboy.deepequal.diff.map.builder;

import com.checkaboy.deepequal.diff.map.IMapDiffCollector;
import com.checkaboy.deepequal.diff.map.MapDiffCollector;
import com.checkaboy.deepequal.diff.map.strategy.IMapDiffCollectionStrategy;
import com.checkaboy.deepequal.diff.map.strategy.OrderedMapCollectionStrategyStrategy;
import com.checkaboy.objectutils.container.Abstract4TypifiedContainer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class MapDiffCollectorBuilder<SK, SV, TK, TV>
        extends Abstract4TypifiedContainer<SK, SV, TK, TV>
        implements IMapDiffCollectorBuilder<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> {

    private IMapDiffCollectionStrategy<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> strategy = new OrderedMapCollectionStrategyStrategy<>(
            ArrayList::new,
            (comparisonContext, source, target) -> Objects.equals(source, target)
    );

    protected MapDiffCollectorBuilder(Class<SK> sourceKeyType, Class<SV> sourceValueType, Class<TK> targetKeyType, Class<TV> targetValueType) {
        super(sourceKeyType, sourceValueType, targetKeyType, targetValueType);
    }

    @Override
    public MapDiffCollectorBuilder<SK, SV, TK, TV> setStrategy(IMapDiffCollectionStrategy<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public IMapDiffCollector<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> build() {
        return new MapDiffCollector<>(strategy);
    }

    public static <SK, SV, TK, TV> MapDiffCollectorBuilder<SK, SV, TK, TV> of(Class<SK> sourceKeyType, Class<SV> sourceValueType, Class<TK> targetKeyType, Class<TV> targetValueType) {
        return new MapDiffCollectorBuilder<>(sourceKeyType, sourceValueType, targetKeyType, targetValueType);
    }

}
