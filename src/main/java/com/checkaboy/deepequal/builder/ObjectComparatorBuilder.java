package com.checkaboy.deepequal.builder;

import com.checkaboy.deepequal.builder.interf.IObjectComparatorBuilder;
import com.checkaboy.deepequal.comparator.ObjectComparator;
import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.interf.IObjectComparator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectComparatorBuilder<O>
        implements IObjectComparatorBuilder<O> {

    private final Class<O> type;
    private Map<String, IFieldComparator<O>> fieldComparatorMap = new HashMap<>();

    public ObjectComparatorBuilder(Class<O> type) {
        this.type = type;
    }

    @Override
    public ObjectComparatorBuilder<O> setFieldComparators(Map<String, IFieldComparator<O>> fieldComparatorMap) {
        this.fieldComparatorMap = fieldComparatorMap;
        return this;
    }

    @Override
    public ObjectComparatorBuilder<O> putFieldComparator(String fieldName, IFieldComparator<O> fieldComparator) {
        fieldComparatorMap.put(fieldName, fieldComparator);
        return this;
    }

    @Override
    public ObjectComparatorBuilder<O> putAllFieldComparators(Map<String, IFieldComparator<O>> fieldComparatorMap) {
        this.fieldComparatorMap.putAll(fieldComparatorMap);
        return this;
    }

    public IObjectComparator<O> build() {
        return new ObjectComparator<>(fieldComparatorMap);
    }

    public Class<O> getType() {
        return type;
    }

}
