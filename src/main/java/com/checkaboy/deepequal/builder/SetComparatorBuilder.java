package com.checkaboy.deepequal.builder;

import com.checkaboy.deepequal.builder.interf.ICollectionComparatorBuilder;
import com.checkaboy.deepequal.comparator.CollectionComparator;
import com.checkaboy.deepequal.comparator.interf.ICollectionComparator;
import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.factory.ICollectionFactory;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public class SetComparatorBuilder<V>
        implements ICollectionComparatorBuilder<Set<V>, V> {

    private final Class<V> type;
    private ICollectionFactory<Set<V>, V> collectionFactory = HashSet::new;
    private IFieldComparator<V> comparator = Objects::equals;

    public SetComparatorBuilder(Class<V> type) {
        this.type = type;
    }

    @Override
    public SetComparatorBuilder<V> setCollectionFactory(ICollectionFactory<Set<V>, V> collectionFactory) {
        this.collectionFactory = collectionFactory;
        return this;
    }

    @Override
    public SetComparatorBuilder<V> setComparator(IFieldComparator<V> comparator) {
        this.comparator = comparator;
        return this;
    }

    public ICollectionComparator<Set<V>, V> build() {
        return new CollectionComparator<>(collectionFactory, comparator);
    }

    public Class<V> getType() {
        return type;
    }

}
