package com.checkaboy.deepequal.factory.general;

import com.checkaboy.deepequal.factory.ISubObjectComparatorFactory;
import com.checkaboy.deepequal.model.single.SubObjectComparator;
import com.checkaboy.deepequal.model.single.interf.IObjectComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class GeneralSubObjectComparatorFactory<O, S>
        implements ISubObjectComparatorFactory<O, S> {

    @Override
    public SubObjectComparator<O, S> createNew(Function<O, S> extractor, IObjectComparator<S> objectComparator) {
        return new SubObjectComparator<>(extractor, objectComparator);
    }

}
