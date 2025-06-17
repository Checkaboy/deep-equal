package com.checkaboy.deepequal.factory;

import com.checkaboy.deepequal.model.single.interf.IComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IFieldComparatorFactory<O, V> {

    IComparator<O> createNew(Function<O, V> extractor, IComparator<V> comparator);

}
