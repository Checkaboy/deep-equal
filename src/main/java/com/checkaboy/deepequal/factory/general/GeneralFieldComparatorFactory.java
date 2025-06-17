package com.checkaboy.deepequal.factory.general;

import com.checkaboy.deepequal.factory.IFieldComparatorFactory;
import com.checkaboy.deepequal.model.single.FieldComparator;
import com.checkaboy.deepequal.model.single.interf.IComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class GeneralFieldComparatorFactory<O, V>
        implements IFieldComparatorFactory<O, V> {

    public IComparator<O> createNew(Function<O, V> extractor, IComparator<V> comparator) {
        return new FieldComparator<>(extractor, comparator);
    }

}
