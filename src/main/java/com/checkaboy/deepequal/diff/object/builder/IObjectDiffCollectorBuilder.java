package com.checkaboy.deepequal.diff.object.builder;

import com.checkaboy.deepequal.IBuilder;
import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectDiffCollectorBuilder<S, T>
        extends IBuilder<IDiffCollector<S, T>> {

    IObjectDiffCollectorBuilder<S, T> setFieldComparators(Map<String, IFieldDiffCollector<S, T>> fieldDiffCollectorMap);

    IObjectDiffCollectorBuilder<S, T> putFieldComparator(String fieldName, IFieldDiffCollector<S, T> fieldDiffCollector);

    IObjectDiffCollectorBuilder<S, T> putAllFieldComparators(Map<String, IFieldDiffCollector<S, T>> fieldDiffCollectorMap);

}
