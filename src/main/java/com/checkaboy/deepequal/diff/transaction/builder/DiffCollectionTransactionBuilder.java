package com.checkaboy.deepequal.diff.transaction.builder;

import com.checkaboy.deepequal.context.factory.IComparisonContextFactory;
import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.transaction.DiffCollectionTransaction;
import com.checkaboy.deepequal.diff.transaction.IDiffCollectionTransaction;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

/**
 * @author Taras Shaptala
 */
public class DiffCollectionTransactionBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IDiffCollectionTransactionBuilder<S, T> {

    private IDiffCollector<S, T> diffCollector;
    private IComparisonContextFactory comparisonContextFactory;
    private IDiffNodeFactory diffNodeFactory;
    private String rootName;

    protected DiffCollectionTransactionBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public DiffCollectionTransactionBuilder<S, T> setDiffCollector(IDiffCollector<S, T> diffCollector) {
        this.diffCollector = diffCollector;
        return this;
    }

    @Override
    public DiffCollectionTransactionBuilder<S, T> setComparisonContextFactory(IComparisonContextFactory comparisonContextFactory) {
        this.comparisonContextFactory = comparisonContextFactory;
        return this;
    }

    @Override
    public DiffCollectionTransactionBuilder<S, T> setDiffNodeFactory(IDiffNodeFactory diffNodeFactory) {
        this.diffNodeFactory = diffNodeFactory;
        return this;
    }

    @Override
    public DiffCollectionTransactionBuilder<S, T> setRootName(String rootName) {
        this.rootName = rootName;
        return this;
    }

    @Override
    public IDiffCollectionTransaction<S, T> build() {
        if(rootName == null) return new DiffCollectionTransaction<>(diffCollector, comparisonContextFactory, diffNodeFactory);
        else return new DiffCollectionTransaction<>(diffCollector, comparisonContextFactory, diffNodeFactory, rootName);
    }

}
