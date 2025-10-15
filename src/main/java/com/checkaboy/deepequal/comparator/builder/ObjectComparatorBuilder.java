package com.checkaboy.deepequal.comparator.builder;

import com.checkaboy.deepequal.comparator.builder.interf.IObjectComparatorBuilder;
import com.checkaboy.deepequal.comparator.model.ObjectComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.model.interf.IObjectComparator;
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
