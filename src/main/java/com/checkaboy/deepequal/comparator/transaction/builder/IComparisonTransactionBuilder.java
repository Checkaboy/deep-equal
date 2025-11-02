package com.checkaboy.deepequal.comparator.transaction.builder;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.array.builder.IArrayComparatorBuilder;
import com.checkaboy.deepequal.comparator.collection.builder.ICollectionComparatorBuilder;
import com.checkaboy.deepequal.comparator.field.builder.IFieldComparatorBuilder;
import com.checkaboy.deepequal.comparator.map.builder.IMapComparatorBuilder;
import com.checkaboy.deepequal.comparator.object.builder.IObjectComparatorBuilder;
import com.checkaboy.deepequal.comparator.transaction.IComparisonTransaction;
import com.checkaboy.deepequal.context.factory.IComparisonContextFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public interface IComparisonTransactionBuilder<S, T> {

    IComparisonTransactionBuilder<S, T> setContextFactory(IComparisonContextFactory factory);

    IComparisonTransactionBuilder<S, T> setComparator(IComparator<S, T> comparator);

    IComparisonTransaction<S, T> build();

}
