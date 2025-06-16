package com.checkaboy.deepequal.builder.interf;

import com.checkaboy.deepequal.factory.IFieldComparatorFactory;
import com.checkaboy.deepequal.model.interf.IComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IFieldComparatorBuilder<O, V> {

    IFieldComparatorBuilder<O, V> setExtractor(Function<O, V> extractor);

    IFieldComparatorBuilder<O, V> setComparator(IComparator<V> comparator);

    void setFactory(IFieldComparatorFactory<O, V> factory);

}
