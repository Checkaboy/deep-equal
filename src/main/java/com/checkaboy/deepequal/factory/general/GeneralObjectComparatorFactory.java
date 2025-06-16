package com.checkaboy.deepequal.factory.general;

import com.checkaboy.deepequal.factory.IObjectComparatorFactory;
import com.checkaboy.deepequal.model.ObjectComparator;
import com.checkaboy.deepequal.model.interf.IComparator;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class GeneralObjectComparatorFactory<O>
        implements IObjectComparatorFactory<O> {

    @Override
    public ObjectComparator<O> createNew(Map<String, IComparator<O>> fieldComparatorMap) {
        return new ObjectComparator<>(fieldComparatorMap);
    }

}
