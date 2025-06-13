package com.checkaboy.deepequal;

import java.util.List;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectEq<O> extends Map<String, IFieldEq<O>> {

    /**
     * @param fieldName name of the field to compare
     * @param first     object being compared
     * @param second    object being compared
     * @return field comparison value by name
     */
    boolean fieldEqual(String fieldName, O first, O second);

    /**
     * @param value  to compare with
     * @param first  object being compared
     * @param second object being compared
     * @return names of all fields that match the value
     */
    List<String> fields(boolean value, O first, O second);

}
