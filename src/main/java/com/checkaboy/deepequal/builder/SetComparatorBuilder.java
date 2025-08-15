package com.checkaboy.deepequal.builder;

import com.checkaboy.deepequal.builder.interf.ICollectionComparatorBuilder;
import com.checkaboy.deepequal.comparator.CollectionComparator;
import com.checkaboy.deepequal.comparator.interf.ICollectionComparator;
import com.checkaboy.deepequal.comparator.interf.IFieldComparator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class SetComparatorBuilder<V>
        implements ICollectionComparatorBuilder<Set<V>, V> {

    private final Class<V> type;
    private Supplier<Set<V>> constructor = HashSet::new;
    private IFieldComparator<V> comparator = Objects::equals;

    public SetComparatorBuilder(Class<V> type) {
        this.type = type;
    }

    @Override
    public SetComparatorBuilder<V> setConstructor(Supplier<Set<V>> constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public SetComparatorBuilder<V> setComparator(IFieldComparator<V> comparator) {
        this.comparator = comparator;
        return this;
    }

    public ICollectionComparator<Set<V>, V> build() {
        return new CollectionComparator<>(constructor, comparator);
    }

    public Class<V> getType() {
        return type;
    }

}
