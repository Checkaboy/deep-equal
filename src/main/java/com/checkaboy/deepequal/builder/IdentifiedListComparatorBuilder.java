package com.checkaboy.deepequal.builder;

import com.checkaboy.deepequal.builder.interf.IIdentifiedCollectionComparatorBuilder;
import com.checkaboy.deepequal.comparator.IdentifiedCollectionComparator;
import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.factory.ICollectionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class IdentifiedListComparatorBuilder<V>
        implements IIdentifiedCollectionComparatorBuilder<List<V>, V> {

    private final Class<V> type;
    private ICollectionFactory<List<V>, V> collectionFactory = ArrayList::new;
    private IFieldComparator<V> identifierComparator = Objects::equals;
    private IFieldComparator<V> comparator = Objects::equals;

    public IdentifiedListComparatorBuilder(Class<V> type) {
        this.type = type;
    }

    @Override
    public IdentifiedListComparatorBuilder<V> setCollectionFactory(ICollectionFactory<List<V>, V> collectionFactory) {
        this.collectionFactory = collectionFactory;
        return this;
    }

    @Override
    public IdentifiedListComparatorBuilder<V> setIdentifierComparator(IFieldComparator<V> identifierComparator) {
        this.identifierComparator = identifierComparator;
        return this;
    }

    @Override
    public IdentifiedListComparatorBuilder<V> setComparator(IFieldComparator<V> comparator) {
        this.comparator = comparator;
        return this;
    }

    public IdentifiedCollectionComparator<List<V>, V> build() {
        return new IdentifiedCollectionComparator<>(collectionFactory, identifierComparator, comparator);
    }

    public Class<V> getType() {
        return type;
    }

}
