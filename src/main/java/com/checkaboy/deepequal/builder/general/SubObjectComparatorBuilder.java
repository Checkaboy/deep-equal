package com.checkaboy.deepequal.builder.general;

import com.checkaboy.deepequal.builder.interf.ISubObjectComparatorBuilder;
import com.checkaboy.deepequal.factory.ISubObjectComparatorFactory;
import com.checkaboy.deepequal.factory.general.GeneralSubObjectComparatorFactory;
import com.checkaboy.deepequal.model.single.ObjectComparator;
import com.checkaboy.deepequal.model.single.interf.IObjectComparator;
import com.checkaboy.deepequal.model.single.interf.ISubObjectComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class SubObjectComparatorBuilder<O, S>
        implements ISubObjectComparatorBuilder<O, S> {

    private Function<O, S> extractor;
    private IObjectComparator<S> objectComparator;
    private ISubObjectComparatorFactory<O, S> factory = new GeneralSubObjectComparatorFactory<>();

    @Override
    public SubObjectComparatorBuilder<O, S> setExtractor(Function<O, S> extractor) {
        this.extractor = extractor;
        return this;
    }

    @Override
    public SubObjectComparatorBuilder<O, S> setObjectComparator(IObjectComparator<S> objectEq) {
        this.objectComparator = objectEq;
        return this;
    }

    @Override
    public void setFactory(ISubObjectComparatorFactory<O, S> factory) {
        this.factory = factory;
    }

    public ISubObjectComparator<O, S> build() {
        if (extractor == null) throw new NullPointerException("extractor can`t be null");
        return factory.createNew(extractor, objectComparator == null ? new ObjectComparator<>() : objectComparator);
    }

}
