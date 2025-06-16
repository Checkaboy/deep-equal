package com.checkaboy.deepequal.builder.safe;

import com.checkaboy.deepequal.model.SubObjectComparator;
import com.checkaboy.deepequal.builder.interf.ISubObjectComparatorBuilder;
import com.checkaboy.deepequal.wrapper.NPESafeFunctionWrapper;
import com.checkaboy.deepequal.model.interf.IObjectComparator;
import com.checkaboy.deepequal.model.interf.ISubObjectComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class SubObjectSafeComparatorBuilder<O, S>
        implements ISubObjectComparatorBuilder<O, S> {

    private Function<O, S> extractor;
    private IObjectComparator<S> objectComparator;

    @Override
    public SubObjectSafeComparatorBuilder<O, S> setExtractor(Function<O, S> extractor) {
        this.extractor = new NPESafeFunctionWrapper<>(extractor);
        return this;
    }

    @Override
    public SubObjectSafeComparatorBuilder<O, S> setObjectComparator(IObjectComparator<S> objectEq) {
        this.objectComparator = objectEq;
        return this;
    }

    public ISubObjectComparator<O, S> build() {
        return new SubObjectComparator<>(extractor, objectComparator);
    }

}
