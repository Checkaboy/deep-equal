package com.checkaboy.deepequal.comparator.strategy.collection;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionComparisonStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV> {

    boolean compare(IComparisonContext comparisonContext, SC source, TC target, IFieldComparator<SV, TV> comparator);

}
