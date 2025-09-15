package com.checkaboy.deepequal.comparator.builder.interf;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectComparatorBuilder<S, T> {

    IObjectComparatorBuilder<S, T> setFieldComparators(Map<String, IFieldComparator<S, T>> fieldComparatorMap);

    IObjectComparatorBuilder<S, T> putFieldComparator(String fieldName, IFieldComparator<S, T> fieldComparator);

    IObjectComparatorBuilder<S, T> putAllFieldComparators(Map<String, IFieldComparator<S, T>> fieldComparatorMap);

}
