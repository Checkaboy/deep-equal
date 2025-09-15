package com.checkaboy.deepequal.comparator.model.interf;

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
    boolean fieldEqual(String fieldName, S source, T target);

    /*
    TODO list of non-matching fields
     */

}
