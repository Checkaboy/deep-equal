package com.checkaboy.deepequal.comparator.collection;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

/**
 * @author Taras Shaptala
 */
public interface IClusterComparisonStrategy<SC, SV, TC, TV> {

    boolean compare(IComparisonContext comparisonContext, SC source, TC target, IFieldComparator<SV, TV> comparator);

}
