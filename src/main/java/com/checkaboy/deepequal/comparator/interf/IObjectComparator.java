package com.checkaboy.deepequal.comparator.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectComparator<O>
        extends IFieldComparator<O>, Map<String, IFieldComparator<O>> {

    /**
     * @param fieldName name of the field to compare
     * @param first     object being compared
     * @param second    object being compared
     * @return field comparison value by name
     */
    boolean fieldEqual(String fieldName, O first, O second);

}
