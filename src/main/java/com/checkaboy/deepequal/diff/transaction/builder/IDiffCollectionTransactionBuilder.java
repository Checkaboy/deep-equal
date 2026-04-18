package com.checkaboy.deepequal.diff.transaction.builder;

import com.checkaboy.deepequal.context.factory.IComparisonContextFactory;
import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.transaction.IDiffCollectionTransaction;
import com.checkaboy.objectutils.model.IBuilder;

/**
 * @author Taras Shaptala
 */
public interface IDiffCollectionTransactionBuilder<S, T>
        extends IBuilder<IDiffCollectionTransaction<S, T>> {

    IDiffCollectionTransactionBuilder<S, T> setDiffCollector(IDiffCollector<S, T> diffCollector);

    IDiffCollectionTransactionBuilder<S, T> setComparisonContextFactory(IComparisonContextFactory comparisonContextFactory);

    IDiffCollectionTransactionBuilder<S, T> setDiffNodeFactory(IDiffNodeFactory diffNodeFactory);

    IDiffCollectionTransactionBuilder<S, T> setRootName(String rootName);

}
