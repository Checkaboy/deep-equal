package com.checkaboy.deepequal.builder.interf;

import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.factory.IMapFactory;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapComparatorBuilder<M extends Map<K, V>, K, V> {

    void setMapFactory(IMapFactory<M, K, V> mapFactory);

    void setComparator(IFieldComparator<V> comparator);

}
