package com.checkaboy.deepequal.builder.general;

import com.checkaboy.deepequal.builder.interf.IFieldComparatorBuilder;
import com.checkaboy.deepequal.factory.IFieldComparatorFactory;
import com.checkaboy.deepequal.factory.general.GeneralFieldComparatorFactory;
import com.checkaboy.deepequal.model.single.interf.IComparator;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldComparatorBuilder<O, V>
        implements IFieldComparatorBuilder<O, V> {

    private Function<O, V> extractor;
    private IComparator<V> comparator = Objects::equals;
    private IFieldComparatorFactory<O, V> factory = new GeneralFieldComparatorFactory<>();

    @Override
    public FieldComparatorBuilder<O, V> setExtractor(Function<O, V> extractor) {
        this.extractor = extractor;
        return this;
    }

    @Override
    public FieldComparatorBuilder<O, V> setComparator(IComparator<V> comparator) {
        this.comparator = comparator;
        return this;
    }

    @Override
    public void setFactory(IFieldComparatorFactory<O, V> factory) {
        this.factory = factory;
    }

    public IComparator<O> build() {
        if(extractor == null) throw new NullPointerException("extractor can`t be null");
        return factory.createNew(extractor, comparator);
    }

}
