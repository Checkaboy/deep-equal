package com.checkaboy.deepequal.diff.strategy.collection;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.diff.container.IDiffNode;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractCollectionDiffCollectorStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionDiffCollectorStrategy<SC, SV, TC, TV> {

    protected final Supplier<Collection<IDiffNode>> constructor;
    protected final IFieldComparator<SV, TV> comparator;

    protected AbstractCollectionDiffCollectorStrategy(Supplier<Collection<IDiffNode>> constructor, IFieldComparator<SV, TV> comparator) {
        this.constructor = constructor;
        this.comparator = comparator;
    }

}
