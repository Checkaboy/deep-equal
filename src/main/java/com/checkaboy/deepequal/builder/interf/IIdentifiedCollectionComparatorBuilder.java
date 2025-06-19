package com.checkaboy.deepequal.builder.interf;

import com.checkaboy.deepequal.comparator.interf.IFieldComparator;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface IIdentifiedCollectionComparatorBuilder<C extends Collection<V>, V>
        extends ICollectionComparatorBuilder<C, V> {

    IIdentifiedCollectionComparatorBuilder<C, V> setIdentifierComparator(IFieldComparator<V> identifierComparator);

}
