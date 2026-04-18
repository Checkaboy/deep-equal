package com.checkaboy.deepequal.comparator.object.builder;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.objectutils.model.IBuilder;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectComparatorBuilder<S, T>
        extends IBuilder<IComparator<S, T>> {

    IObjectComparatorBuilder<S, T> set(Map<String, IFieldComparator<S, T>> fieldComparatorMap);

    IObjectComparatorBuilder<S, T> put(String fieldName, IFieldComparator<S, T> fieldComparator);

    IObjectComparatorBuilder<S, T> putAll(Map<String, IFieldComparator<S, T>> fieldComparatorMap);

}
