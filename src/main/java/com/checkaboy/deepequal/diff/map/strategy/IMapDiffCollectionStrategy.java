package com.checkaboy.deepequal.diff.map.strategy;

import com.checkaboy.deepequal.diff.collection.strategy.IClusterDiffCollectionStrategy;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapDiffCollectionStrategy<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IClusterDiffCollectionStrategy<SM, TM> {
}
