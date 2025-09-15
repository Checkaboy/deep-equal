package com.checkaboy.deepequal;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface IComparator<S, T> {

    /**
     * Basic comparison method
     *
     * @param source  object being compared
     * @param target object being compared
     * @return equal or not
     */
    boolean equal(S source, T target);

}
