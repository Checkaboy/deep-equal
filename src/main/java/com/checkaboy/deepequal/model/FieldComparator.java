package com.checkaboy.deepequal.model;

import com.checkaboy.deepequal.model.interf.IComparator;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldComparator<O, V>
        implements IComparator<O> {

    private final Function<O, V> extractor;
    private final IComparator<V> comparator;

    public FieldComparator(Function<O, V> extractor, IComparator<V> comparator) {
        this.extractor = extractor;
        this.comparator = comparator;
    }

    @Override
    public boolean equal(O first, O second) {
        return comparator.equal(extractor.apply(first), extractor.apply(second));
    }

    public static <O, V> IComparator<O> simpleFieldComparator(Function<O, V> extractor) {
        return new FieldComparator<>(extractor, Objects::equals);
    }

}
