package com.checkaboy.deepequal.builder.interf;

import com.checkaboy.deepequal.factory.ISubObjectComparatorFactory;
import com.checkaboy.deepequal.model.single.interf.IObjectComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface ISubObjectComparatorBuilder<O, S> {

    ISubObjectComparatorBuilder<O, S> setExtractor(Function<O, S> extractor);

    ISubObjectComparatorBuilder<O, S> setObjectComparator(IObjectComparator<S> objectEq);

    void setFactory(ISubObjectComparatorFactory<O, S> factory);

}
