package com.checkaboy.deepequal.diff.model.interf;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionDiffCollector<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IDiffCollector<SC, TC> {
}
