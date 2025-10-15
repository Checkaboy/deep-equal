package com.checkaboy.deepequal.comparator.builder.interf;

import com.checkaboy.deepequal.comparator.model.interf.IComparator;

/**
 * @author Taras Shaptala
 */
public interface IComparatorBuilder<S, T> {

    IComparator<S, T> build();

}
