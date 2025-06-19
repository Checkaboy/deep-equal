package com.checkaboy.deepequal.comparator;

import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.wrapper.NPESafeFunctionWrapper;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldComparator<O, V>
        implements IFieldComparator<O> {

    private final Function<O, V> extractor;
    private final IFieldComparator<V> comparator;

    public FieldComparator(Function<O, V> extractor, IFieldComparator<V> comparator) {
        this.extractor = new NPESafeFunctionWrapper<>(extractor);
        this.comparator = comparator;
    }

    @Override
    public boolean equal(O first, O second) {
        return comparator.equal(extractor.apply(first), extractor.apply(second));
    }

    public static <O, V> IFieldComparator<O> simpleFieldComparator(Function<O, V> extractor) {
        return new FieldComparator<>(extractor, Objects::equals);
    }

}
