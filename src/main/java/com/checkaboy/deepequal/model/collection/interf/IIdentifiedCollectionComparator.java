package com.checkaboy.deepequal.model.collection.interf;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface IIdentifiedCollectionComparator<C extends Collection<V>, V>
        extends ICollectionComparator<C, V> {

    C objectsNotContainsInSecondListButEqualsByIdentifier(C first, C second);

}
