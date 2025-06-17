package com.checkaboy.deepequal.builder.safe;

import com.checkaboy.deepequal.factory.IFieldComparatorFactory;
import com.checkaboy.deepequal.model.single.FieldComparator;
import com.checkaboy.deepequal.builder.interf.IFieldComparatorBuilder;
import com.checkaboy.deepequal.wrapper.NPESafeFunctionWrapper;
import com.checkaboy.deepequal.model.single.interf.IComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldSafeComparatorBuilder<O, V>
        implements IFieldComparatorBuilder<O, V> {

    private Function<O, V> extractor;
    private IComparator<V> comparator;

    @Override
    public FieldSafeComparatorBuilder<O, V> setExtractor(Function<O, V> extractor) {
        this.extractor = new NPESafeFunctionWrapper<>(extractor);
        return this;
    }

    @Override
    public FieldSafeComparatorBuilder<O, V> setComparator(IComparator<V> comparator) {
        this.comparator = comparator;
        return this;
    }

    @Override
    public void setFactory(IFieldComparatorFactory<O, V> factory) {

    }

    public IComparator<O> build() {
        return new FieldComparator<>(extractor, comparator);
    }

}
