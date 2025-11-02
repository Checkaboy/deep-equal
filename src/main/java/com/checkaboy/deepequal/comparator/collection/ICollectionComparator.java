package com.checkaboy.deepequal.comparator.collection;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionComparator<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IFieldComparator<SC, TC> {
}
