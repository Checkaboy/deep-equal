package com.checkaboy.deepequal.comparator.object.builder;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.comparator.object.IObjectComparator;
import com.checkaboy.deepequal.comparator.object.ObjectComparator;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectComparatorBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IObjectComparatorBuilder<S, T> {

    private Map<String, IFieldComparator<S, T>> fieldComparatorMap = new HashMap<>();

    public ObjectComparatorBuilder(Class<S> sourceClass, Class<T> targetClass) {
        super(sourceClass, targetClass);
    }

    @Override
    public ObjectComparatorBuilder<S, T> setFieldComparators(Map<String, IFieldComparator<S, T>> fieldComparatorMap) {
        this.fieldComparatorMap = fieldComparatorMap;
        return this;
    }

    @Override
    public ObjectComparatorBuilder<S, T> putFieldComparator(String fieldName, IFieldComparator<S, T> fieldComparator) {
        fieldComparatorMap.put(fieldName, fieldComparator);
        return this;
    }

    @Override
    public ObjectComparatorBuilder<S, T> putAllFieldComparators(Map<String, IFieldComparator<S, T>> fieldComparatorMap) {
        this.fieldComparatorMap.putAll(fieldComparatorMap);
        return this;
    }

    @Override
    public IObjectComparator<S, T> build() {
        return new ObjectComparator<>(fieldComparatorMap);
    }

}
