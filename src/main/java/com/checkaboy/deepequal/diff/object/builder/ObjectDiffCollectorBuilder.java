package com.checkaboy.deepequal.diff.object.builder;

import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;
import com.checkaboy.deepequal.diff.object.IObjectDiffCollector;
import com.checkaboy.deepequal.diff.object.ObjectDiffCollector;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectDiffCollectorBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IObjectDiffCollectorBuilder<S, T> {

    private Map<String, IFieldDiffCollector<S, T>> fieldDiffCollectorMap = new HashMap<>();

    protected ObjectDiffCollectorBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public ObjectDiffCollectorBuilder<S, T> setFieldComparators(Map<String, IFieldDiffCollector<S, T>> fieldDiffCollectorMap) {
        this.fieldDiffCollectorMap = fieldDiffCollectorMap;
        return this;
    }

    @Override
    public ObjectDiffCollectorBuilder<S, T> putFieldComparator(String fieldName, IFieldDiffCollector<S, T> fieldDiffCollector) {
        fieldDiffCollectorMap.put(fieldName, fieldDiffCollector);
        return this;
    }

    @Override
    public ObjectDiffCollectorBuilder<S, T> putAllFieldComparators(Map<String, IFieldDiffCollector<S, T>> fieldDiffCollectorMap) {
        this.fieldDiffCollectorMap.putAll(fieldDiffCollectorMap);
        return this;
    }

    @Override
    public IObjectDiffCollector<S, T> build() {
        return new ObjectDiffCollector<>(fieldDiffCollectorMap);
    }

}
