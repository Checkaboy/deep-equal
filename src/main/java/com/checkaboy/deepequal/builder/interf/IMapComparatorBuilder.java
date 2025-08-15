package com.checkaboy.deepequal.builder.interf;

import com.checkaboy.deepequal.comparator.interf.IFieldComparator;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public interface IMapComparatorBuilder<M extends Map<K, V>, K, V> {

    IMapComparatorBuilder<M, K, V> setConstructor(Supplier<Map<K, V>> constructor);

    IMapComparatorBuilder<M, K, V> setComparator(IFieldComparator<V> comparator);

}
