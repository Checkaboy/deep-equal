package com.checkaboy.deepequal.diff.model;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.model.interf.IArrayDiffCollector;

/**
 * @author Taras Shaptala
 */
public class ArrayDiffCollector<S, T>
        implements IArrayDiffCollector<S, T> {

    @Override
    public IDiffNode collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, S[] source, T[] target, String currentPath) {
        return null;
    }

}
