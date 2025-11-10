package com.checkaboy.deepequal.diff.transaction;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.context.factory.IComparisonContextFactory;
import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;

/**
 * @author Taras Shaptala
 */
public class DiffCollectionTransaction<S, T>
        implements IDiffCollectionTransaction<S, T> {

    private final IDiffCollector<S, T> diffCollector;
    private final IComparisonContextFactory comparisonContextFactory;
    private final IDiffNodeFactory diffNodeFactory;
    private String rootName;

    public DiffCollectionTransaction(IDiffCollector<S, T> diffCollector, IComparisonContextFactory comparisonContextFactory, IDiffNodeFactory diffNodeFactory) {
        this(diffCollector, comparisonContextFactory, diffNodeFactory, "");
    }

    public DiffCollectionTransaction(IDiffCollector<S, T> diffCollector, IComparisonContextFactory comparisonContextFactory, IDiffNodeFactory diffNodeFactory, String rootName) {
        this.diffCollector = diffCollector;
        this.comparisonContextFactory = comparisonContextFactory;
        this.diffNodeFactory = diffNodeFactory;
        this.rootName = rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    @Override
    public IDiffNode collect(S source, T target, String currentPath) {
        IComparisonContext comparisonContext = comparisonContextFactory.create();
        return diffCollector.collect(
                comparisonContext,
                diffNodeFactory,
                source,
                target,
                rootName
        );
    }

}
