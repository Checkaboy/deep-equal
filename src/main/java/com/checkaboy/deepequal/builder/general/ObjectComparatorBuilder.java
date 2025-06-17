package com.checkaboy.deepequal.builder.general;

import com.checkaboy.deepequal.builder.interf.IObjectComparatorBuilder;
import com.checkaboy.deepequal.factory.IObjectComparatorFactory;
import com.checkaboy.deepequal.factory.general.GeneralObjectComparatorFactory;
import com.checkaboy.deepequal.model.single.interf.IComparator;
import com.checkaboy.deepequal.model.single.interf.IObjectComparator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectComparatorBuilder<O>
        implements IObjectComparatorBuilder<O> {

    private Map<String, IComparator<O>> fieldComparatorMap = new HashMap<>();
    private IObjectComparatorFactory<O> factory = new GeneralObjectComparatorFactory<>();

    @Override
    public ObjectComparatorBuilder<O> setFieldComparatorMap(Map<String, IComparator<O>> fieldEqMap) {
        this.fieldComparatorMap = fieldEqMap;
        return this;
    }

    @Override
    public ObjectComparatorBuilder<O> putFieldComparator(String fieldName, IComparator<O> comparator) {
        if (fieldComparatorMap == null) fieldComparatorMap = new HashMap<>();
        fieldComparatorMap.put(fieldName, comparator);
        return this;
    }

    @Override
    public ObjectComparatorBuilder<O> putAllFieldComparators(Map<String, IComparator<O>> fieldComparatorMap) {
        if (this.fieldComparatorMap == null) this.fieldComparatorMap = fieldComparatorMap;
        else this.fieldComparatorMap.putAll(fieldComparatorMap);
        return this;
    }

    @Override
    public void setFactory(IObjectComparatorFactory<O> factory) {
        this.factory = factory;
    }

    public IObjectComparator<O> build() {
        return factory.createNew(fieldComparatorMap);
    }

}
