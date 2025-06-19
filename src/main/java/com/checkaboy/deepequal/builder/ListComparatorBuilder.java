package com.checkaboy.deepequal.builder;

import com.checkaboy.deepequal.builder.interf.ICollectionComparatorBuilder;
import com.checkaboy.deepequal.comparator.CollectionComparator;
import com.checkaboy.deepequal.comparator.interf.ICollectionComparator;
import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.factory.ICollectionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class ListComparatorBuilder<V>
        implements ICollectionComparatorBuilder<List<V>, V> {

    private final Class<V> type;
    private ICollectionFactory<List<V>, V> collectionFactory = ArrayList::new;
    private IFieldComparator<V> comparator = Objects::equals;

    public ListComparatorBuilder(Class<V> type) {
        this.type = type;
    }

    @Override
    public ListComparatorBuilder<V> setCollectionFactory(ICollectionFactory<List<V>, V> collectionFactory) {
        this.collectionFactory = collectionFactory;
        return this;
    }

    @Override
    public ListComparatorBuilder<V> setComparator(IFieldComparator<V> comparator) {
        this.comparator = comparator;
        return this;
    }

    public ICollectionComparator<List<V>, V> build() {
        return new CollectionComparator<>(collectionFactory, comparator);
    }

    public Class<V> getType() {
        return type;
    }

}
