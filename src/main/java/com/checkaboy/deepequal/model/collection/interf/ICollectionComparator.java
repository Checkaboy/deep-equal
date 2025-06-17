package com.checkaboy.deepequal.model.collection.interf;

import com.checkaboy.deepequal.model.single.interf.IComparator;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionComparator<C extends Collection<V>, V>
        extends IComparator<C> {

    C objectsNotContainsInSecondList(C first, C second);

}
