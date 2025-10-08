package com.checkaboy.deepequal.diff.model.interf;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IFieldDifference;

import java.util.List;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface IDiffCollector<S, T> {

    List<IFieldDifference> collectDifferences(IComparisonContext comparisonContext, S source, T target);

}
