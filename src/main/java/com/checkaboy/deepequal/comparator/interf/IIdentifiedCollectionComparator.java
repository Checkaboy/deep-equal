package com.checkaboy.deepequal.comparator.interf;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface IIdentifiedCollectionComparator<C extends Collection<V>, V>
        extends ICollectionComparator<C, V> {

    C objectsNotContainsInSecondCollectionButEqualsByIdentifier(C first, C second);

}
