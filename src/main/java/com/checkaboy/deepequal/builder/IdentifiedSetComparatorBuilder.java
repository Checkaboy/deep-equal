package com.checkaboy.deepequal.builder;

import com.checkaboy.deepequal.builder.interf.IIdentifiedCollectionComparatorBuilder;
import com.checkaboy.deepequal.comparator.IdentifiedCollectionComparator;
import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.factory.ICollectionFactory;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public class IdentifiedSetComparatorBuilder<V>
        implements IIdentifiedCollectionComparatorBuilder<Set<V>, V> {

    private final Class<V> type;
    private ICollectionFactory<Set<V>, V> collectionFactory = HashSet::new;
    private IFieldComparator<V> identifierComparator = Objects::equals;
    private IFieldComparator<V> comparator = Objects::equals;

    public IdentifiedSetComparatorBuilder(Class<V> type) {
        this.type = type;
    }

    @Override
    public IdentifiedSetComparatorBuilder<V> setCollectionFactory(ICollectionFactory<Set<V>, V> collectionFactory) {
        this.collectionFactory = collectionFactory;
        return this;
    }

    @Override
    public IdentifiedSetComparatorBuilder<V> setIdentifierComparator(IFieldComparator<V> identifierComparator) {
        this.identifierComparator = identifierComparator;
        return this;
    }

    @Override
    public IdentifiedSetComparatorBuilder<V> setComparator(IFieldComparator<V> comparator) {
        this.comparator = comparator;
        return this;
    }

    public IdentifiedCollectionComparator<Set<V>, V> build() {
        return new IdentifiedCollectionComparator<>(collectionFactory, identifierComparator, comparator);
    }

    public Class<V> getType() {
        return type;
    }

}
