package com.checkaboy.deepequal.diff.collection.builder;

import com.checkaboy.deepequal.IBuilder;
import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.collection.strategy.ICollectionDiffCollectionStrategy;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionDiffCollectorBuilder<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IBuilder<IDiffCollector<SC, TC>> {

    ICollectionDiffCollectorBuilder<SC, SV, TC, TV> setStrategy(ICollectionDiffCollectionStrategy<SC, SV, TC, TV> strategy);

}
