package com.checkaboy.deepequal.diff.object.builder;

import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;
import com.checkaboy.objectutils.model.IBuilder;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectDiffCollectorBuilder<S, T>
        extends IBuilder<IDiffCollector<S, T>> {

    IObjectDiffCollectorBuilder<S, T> set(Map<String, IFieldDiffCollector<S, T>> fieldDiffCollectorMap);

    IObjectDiffCollectorBuilder<S, T> put(String fieldName, IFieldDiffCollector<S, T> fieldDiffCollector);

    IObjectDiffCollectorBuilder<S, T> putAll(Map<String, IFieldDiffCollector<S, T>> fieldDiffCollectorMap);

}
