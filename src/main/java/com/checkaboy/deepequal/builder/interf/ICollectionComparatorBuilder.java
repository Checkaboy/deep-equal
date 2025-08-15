package com.checkaboy.deepequal.builder.interf;

import com.checkaboy.deepequal.comparator.interf.IFieldComparator;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public interface ICollectionComparatorBuilder<C extends Collection<V>, V> {

    ICollectionComparatorBuilder<C, V> setConstructor(Supplier<C> constructor);

    ICollectionComparatorBuilder<C, V> setComparator(IFieldComparator<V> comparator);

}
