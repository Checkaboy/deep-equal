package com.checkaboy.deepequal.builder.interf;

import com.checkaboy.deepequal.factory.IObjectComparatorFactory;
import com.checkaboy.deepequal.model.interf.IComparator;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectComparatorBuilder<O> {

    IObjectComparatorBuilder<O> setFieldComparatorMap(Map<String, IComparator<O>> fieldEqMap);

    IObjectComparatorBuilder<O> putFieldComparator(String fieldName, IComparator<O> comparator);

    IObjectComparatorBuilder<O> putAllFieldComparators(Map<String, IComparator<O>> fieldEqMap);

    void setFactory(IObjectComparatorFactory<O> factory);

}
