package com.checkaboy.deepequal.model.interf;

/**
 * @author Taras Shaptala
 */
public interface IComparator<O> {

    /**
     * Basic comparison method
     *
     * @param first  object being compared
     * @param second object being compared
     * @return equal or not
     */
    boolean equal(O first, O second);

}
