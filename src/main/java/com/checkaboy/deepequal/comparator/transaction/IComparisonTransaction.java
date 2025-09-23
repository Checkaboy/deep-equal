package com.checkaboy.deepequal.comparator.transaction;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface IComparisonTransaction<S, T> {

    boolean compare(S source, T target);

}
