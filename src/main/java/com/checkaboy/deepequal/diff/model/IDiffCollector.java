package com.checkaboy.deepequal.diff.model;

import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.List;

/**
 * @author Taras Shaptala
 */
public interface IDiffCollector<S, T> {

    List<IFieldDifference> collectDifferences(IComparisonContext ctx, S source, T target);

}
