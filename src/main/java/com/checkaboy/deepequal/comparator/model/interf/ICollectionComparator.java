package com.checkaboy.deepequal.comparator.model.interf;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionComparator<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IFieldComparator<SC, TC> {
}
