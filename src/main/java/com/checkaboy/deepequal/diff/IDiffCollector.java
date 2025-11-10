package com.checkaboy.deepequal.diff;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface IDiffCollector<S, T> {

    IDiffNode collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, S source, T target, String currentPath);

}
