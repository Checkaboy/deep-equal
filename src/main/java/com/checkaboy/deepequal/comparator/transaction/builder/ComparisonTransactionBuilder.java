package com.checkaboy.deepequal.comparator.transaction.builder;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.array.builder.ArrayComparatorBuilder;
import com.checkaboy.deepequal.comparator.collection.builder.ListComparatorBuilder;
import com.checkaboy.deepequal.comparator.collection.builder.SetComparatorBuilder;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.comparator.field.builder.FieldComparatorBuilder;
import com.checkaboy.deepequal.comparator.map.builder.MapComparatorBuilder;
import com.checkaboy.deepequal.comparator.object.builder.IObjectComparatorBuilder;
import com.checkaboy.deepequal.comparator.object.builder.ObjectComparatorBuilder;
import com.checkaboy.deepequal.comparator.transaction.ComparisonTransaction;
import com.checkaboy.deepequal.comparator.transaction.IComparisonTransaction;
import com.checkaboy.deepequal.context.cache.ComparisonContext;
import com.checkaboy.deepequal.context.factory.IComparisonContextFactory;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ComparisonTransactionBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IComparisonTransactionBuilder<S, T> {

    private IComparisonContextFactory comparisonContextFactory = ComparisonContext::new;
    private IComparator<S, T> comparator;

    public ComparisonTransactionBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public ComparisonTransactionBuilder<S, T> setContextFactory(IComparisonContextFactory factory) {
        this.comparisonContextFactory = factory;
        return this;
    }

    @Override
    public ComparisonTransactionBuilder<S, T> setComparator(IComparator<S, T> comparator) {
        this.comparator = comparator;
        return this;
    }

    @Override
    public IComparisonTransaction<S, T> build() {
        return new ComparisonTransaction<>(comparator, comparisonContextFactory);
    }

}
