package com.checkaboy.deepequal.model.single;

import com.checkaboy.deepequal.model.single.interf.IObjectComparator;
import com.checkaboy.deepequal.model.single.interf.ISubObjectComparator;

import java.util.List;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class SubObjectComparator<O, S>
        extends AbstractMapObjectComparator<S>
        implements ISubObjectComparator<O, S> {

    private final Function<O, S> extractor;
    private final IObjectComparator<S> objectComparator;

    public SubObjectComparator(Function<O, S> extractor, IObjectComparator<S> fieldComparatorMap) {
        this.extractor = extractor;
        this.objectComparator = fieldComparatorMap;
    }

    @Override
    public boolean equal(O first, O second) {
        return objectComparator.equal(extractor.apply(first), extractor.apply(second));
    }

    @Override
    public boolean fieldEqual(String fieldName, S first, S second) {
        return false;
    }

    @Override
    public List<String> fields(boolean value, S first, S second) {
        return List.of();
    }

}
