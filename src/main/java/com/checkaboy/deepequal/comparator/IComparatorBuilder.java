package com.checkaboy.deepequal.comparator;

/**
 * @author Taras Shaptala
 */
public interface IComparatorBuilder<S, T> {

    IComparator<S, T> build();

}
