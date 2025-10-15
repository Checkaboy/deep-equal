package com.checkaboy.deepequal.comparator.model.interf;

import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectComparator<S, T>
        extends IFieldComparator<S, T>, Map<String, IFieldComparator<S, T>> {

    /**
     * @param fieldName name of the field to compare
     * @param source    object being compared
     * @param target    object being compared
     * @return field comparison value by name
     */
    boolean fieldEqual(IComparisonContext comparisonContext, String fieldName, S source, T target);

}
