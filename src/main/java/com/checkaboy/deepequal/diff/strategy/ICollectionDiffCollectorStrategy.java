package com.checkaboy.deepequal.diff.strategy;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.model.interf.IFieldDiffCollector;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionDiffCollectorStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV> {

    Collection<IDiffNode> collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SC source, TC target, String currentPath, IFieldDiffCollector<SV, TV> diffCollector);

}
