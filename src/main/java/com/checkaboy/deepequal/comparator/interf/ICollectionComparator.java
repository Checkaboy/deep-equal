package com.checkaboy.deepequal.comparator.interf;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionComparator<C extends Collection<V>, V>
        extends IFieldComparator<C> {

    C objectsNotContainsInSecondCollection(C first, C second);

}
