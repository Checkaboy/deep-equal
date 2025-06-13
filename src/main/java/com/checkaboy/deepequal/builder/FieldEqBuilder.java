package com.checkaboy.deepequal.builder;

import com.checkaboy.deepequal.FieldEq;
import com.checkaboy.deepequal.IFieldEq;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldEqBuilder<O, V>
        implements IFieldEqBuilder<O, V> {

    private Function<O, V> extractor;
    private IFieldEq<V> comparator;

    @Override
    public void setExtractor(Function<O, V> extractor) {
        this.extractor = extractor;
    }

    @Override
    public void setComparator(IFieldEq<V> comparator) {
        this.comparator = comparator;
    }

    public IFieldEq<O> build() {
        return new FieldEq<>(extractor, comparator);
    }

}
