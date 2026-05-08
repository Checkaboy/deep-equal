package com.checkaboy.deepequal.comparator.object.builder;

import com.checkaboy.deepequal.IBuilder;
import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectComparatorBuilder<S, T>
        extends IBuilder<IComparator<S, T>> {

    IObjectComparatorBuilder<S, T> setFieldComparators(Map<String, IFieldComparator<S, T>> fieldComparatorMap);

    IObjectComparatorBuilder<S, T> putFieldComparator(String fieldName, IFieldComparator<S, T> fieldComparator);

    IObjectComparatorBuilder<S, T> putAllFieldComparators(Map<String, IFieldComparator<S, T>> fieldComparatorMap);

}
