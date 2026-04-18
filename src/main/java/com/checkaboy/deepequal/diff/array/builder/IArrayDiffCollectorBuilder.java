package com.checkaboy.deepequal.diff.array.builder;

import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.array.strategy.IArrayDiffCollectionStrategy;
import com.checkaboy.objectutils.model.IBuilder;

/**
 * @author Taras Shaptala
 */
public interface IArrayDiffCollectorBuilder<S, T>
        extends IBuilder<IDiffCollector<S[], T[]>> {

    IArrayDiffCollectorBuilder<S, T> setStrategy(IArrayDiffCollectionStrategy<S, T> strategy);

}
