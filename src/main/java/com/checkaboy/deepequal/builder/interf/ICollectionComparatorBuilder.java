package com.checkaboy.deepequal.builder.interf;

import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.factory.ICollectionFactory;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionComparatorBuilder<C extends Collection<V>, V> {

    ICollectionComparatorBuilder<C, V> setCollectionFactory(ICollectionFactory<C, V> collectionFactory);

    ICollectionComparatorBuilder<C, V> setComparator(IFieldComparator<V> comparator);

}
