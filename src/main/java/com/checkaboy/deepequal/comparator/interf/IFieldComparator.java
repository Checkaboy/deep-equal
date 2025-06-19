package com.checkaboy.deepequal.comparator.interf;

/**
 * @author Taras Shaptala
 */
public interface IFieldComparator<O> {

    /**
     * Basic comparison method
     *
     * @param first  object being compared
     * @param second object being compared
     * @return equal or not
     */
    boolean equal(O first, O second);

}
