package com.checkaboy.deepequal.diff.collection;

import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionDiffCollector<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IFieldDiffCollector<SC, TC> {
}
