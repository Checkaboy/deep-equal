package com.checkaboy.deepequal.builder;

import com.checkaboy.deepequal.builder.interf.IFieldComparatorBuilder;
import com.checkaboy.deepequal.comparator.FieldComparator;
import com.checkaboy.deepequal.comparator.interf.IFieldComparator;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldComparatorBuilder<O, V>
        implements IFieldComparatorBuilder<O, V> {

    private final Class<O> type;
    private Function<O, V> extractor = o -> null;
    private IFieldComparator<V> comparator = Objects::equals;

    public FieldComparatorBuilder(Class<O> type) {
        this.type = type;
    }

    @Override
    public FieldComparatorBuilder<O, V> setExtractor(Function<O, V> extractor) {
        this.extractor = extractor;
        return this;
    }

    @Override
    public FieldComparatorBuilder<O, V> setComparator(IFieldComparator<V> comparator) {
        this.comparator = comparator;
        return this;
    }

    public IFieldComparator<O> build() {
        return new FieldComparator<>(extractor, comparator);
    }

    public Class<O> getType() {
        return type;
    }

}
