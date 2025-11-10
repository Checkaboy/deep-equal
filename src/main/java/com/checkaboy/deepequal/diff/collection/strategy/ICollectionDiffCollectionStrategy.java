package com.checkaboy.deepequal.diff.collection.strategy;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionDiffCollectionStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IClusterDiffCollectionStrategy<SC, TC> {
}
