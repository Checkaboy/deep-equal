package com.checkaboy.deepequal.builder.interf;

import com.checkaboy.deepequal.comparator.interf.IFieldComparator;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectComparatorBuilder<O> {

    IObjectComparatorBuilder<O> setFieldComparators(Map<String, IFieldComparator<O>> fieldComparatorMap);

    IObjectComparatorBuilder<O> putFieldComparator(String fieldName, IFieldComparator<O> fieldComparator);

    IObjectComparatorBuilder<O> putAllFieldComparators(Map<String, IFieldComparator<O>> fieldComparatorMap);

}
