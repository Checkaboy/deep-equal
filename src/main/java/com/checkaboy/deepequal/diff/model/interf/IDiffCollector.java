package com.checkaboy.deepequal.diff.model.interf;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface IDiffCollector<S, T> {

    IDiffNode collect(IComparisonContext comparisonContext, S source, T target, String currentPath);

}
