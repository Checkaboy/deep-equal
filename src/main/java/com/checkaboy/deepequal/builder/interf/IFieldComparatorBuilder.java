package com.checkaboy.deepequal.builder.interf;

import com.checkaboy.deepequal.comparator.interf.IFieldComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IFieldComparatorBuilder<O, V> {

    IFieldComparatorBuilder<O, V> setExtractor(Function<O, V> extractor);

    IFieldComparatorBuilder<O, V> setComparator(IFieldComparator<V> comparator);

}
