package com.checkaboy.deepequal.comparator.strategy;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

/**
 * @author Taras Shaptala
 */
public interface IClusterComparisonStrategy<SC, SV, TC, TV> {

    boolean compare(IComparisonContext comparisonContext, SC source, TC target, IFieldComparator<SV, TV> comparator);

}
