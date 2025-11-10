package com.checkaboy.deepequal.diff.array.builder;

import com.checkaboy.deepequal.IBuilder;
import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.array.strategy.IArrayDiffCollectionStrategy;

/**
 * @author Taras Shaptala
 */
public interface IArrayDiffCollectorBuilder<S, T>
        extends IBuilder<IDiffCollector<S[], T[]>> {

    IArrayDiffCollectorBuilder<S, T> setStrategy(IArrayDiffCollectionStrategy<S, T> strategy);

}
