package com.checkaboy.deepequal.builder;

import com.checkaboy.deepequal.IFieldEq;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IFieldEqBuilder<O, V> {

    void setExtractor(Function<O, V> extractor);

    void setComparator(IFieldEq<V> comparator);

}
