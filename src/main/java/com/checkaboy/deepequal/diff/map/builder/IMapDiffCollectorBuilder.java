package com.checkaboy.deepequal.diff.map.builder;

import com.checkaboy.deepequal.IBuilder;
import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.map.strategy.IMapDiffCollectionStrategy;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapDiffCollectorBuilder<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IBuilder<IDiffCollector<SM, TM>> {

    IMapDiffCollectorBuilder<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> setStrategy(IMapDiffCollectionStrategy<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> strategy);

}
