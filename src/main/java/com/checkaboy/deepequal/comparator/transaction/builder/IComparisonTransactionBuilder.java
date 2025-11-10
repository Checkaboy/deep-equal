package com.checkaboy.deepequal.comparator.transaction.builder;

import com.checkaboy.deepequal.IBuilder;
import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.transaction.IComparisonTransaction;
import com.checkaboy.deepequal.context.factory.IComparisonContextFactory;

/**
 * @author Taras Shaptala
 */
public interface IComparisonTransactionBuilder<S, T>
        extends IBuilder<IComparisonTransaction<S, T>> {

    IComparisonTransactionBuilder<S, T> setContextFactory(IComparisonContextFactory factory);

    IComparisonTransactionBuilder<S, T> setComparator(IComparator<S, T> comparator);

}
