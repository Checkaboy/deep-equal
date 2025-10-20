package com.checkaboy.deepequal.diff.strategy.array;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface IArrayDiffCollectorStrategy<S, T> {

    Collection<IDiffNode> collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, S[] source, T[] target, String currentPath);

}
