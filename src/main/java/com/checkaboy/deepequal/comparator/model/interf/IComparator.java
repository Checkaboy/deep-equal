package com.checkaboy.deepequal.comparator.model.interf;

import com.checkaboy.deepequal.context.cache.IComparisonContext;

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
    boolean compare(IComparisonContext comparisonContext, S source, T target);

}
