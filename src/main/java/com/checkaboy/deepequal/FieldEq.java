package com.checkaboy.deepequal;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldEq<O, V>
        implements IFieldEq<O> {

    private final Function<O, V> extractor;
    private final IFieldEq<V> comparator;

    public FieldEq(Function<O, V> extractor, IFieldEq<V> comparator) {
        this.extractor = extractor;
        this.comparator = comparator;
    }

    @Override
    public boolean equal(O first, O second) {
        return comparator.equal(extractor.apply(first), extractor.apply(second));
    }

}
