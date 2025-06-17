package com.checkaboy.deepequal.factory;

import com.checkaboy.deepequal.model.single.SubObjectComparator;
import com.checkaboy.deepequal.model.single.interf.IObjectComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface ISubObjectComparatorFactory<O, S> {

    SubObjectComparator<O, S> createNew(Function<O, S> extractor, IObjectComparator<S> objectComparator);

}
