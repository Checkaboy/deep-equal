package com.checkaboy.deepequal.model.single.interf;

import java.util.List;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface ISubObjectComparator<O, S>
        extends IComparator<O>, Map<String, IComparator<S>> {

    /**
     * @param fieldName name of the field to compare
     * @param first     object being compared
     * @param second    object being compared
     * @return field comparison value by name
     */
    boolean fieldEqual(String fieldName, S first, S second);

    /**
     * @param value  to compare with
     * @param first  object being compared
     * @param second object being compared
     * @return names of all fields that match the value
     */
    List<String> fields(boolean value, S first, S second);

}
