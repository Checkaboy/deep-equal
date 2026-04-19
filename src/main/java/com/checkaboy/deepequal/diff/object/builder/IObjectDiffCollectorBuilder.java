package com.checkaboy.deepequal.diff.object.builder;

import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;
import com.checkaboy.deepequal.dsl.NamedFieldDiffCollector;
import com.checkaboy.objectutils.model.IBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IObjectDiffCollectorBuilder<S, T>
        extends IBuilder<IDiffCollector<S, T>> {

    IObjectDiffCollectorBuilder<S, T> set(Map<String, ? extends IFieldDiffCollector<S, T>> fieldDiffCollectorMap);

    IObjectDiffCollectorBuilder<S, T> put(String fieldName, IFieldDiffCollector<S, T> fieldDiffCollector);

    IObjectDiffCollectorBuilder<S, T> put(NamedFieldDiffCollector<S, T> namedFieldDiffCollector);

    <SV, TV> IObjectDiffCollectorBuilder<S, T> put(
            String fieldName,
            Function<S, SV> sourceExtractor,
            Function<T, TV> targetExtractor,
            IDiffCollector<SV, TV> diffCollector
    );

    <V> IObjectDiffCollectorBuilder<S, T> put(
            String fieldName,
            Function<S, V> sourceExtractor,
            Function<T, V> targetExtractor
    );

    IObjectDiffCollectorBuilder<S, T> putAll(Map<String, ? extends IFieldDiffCollector<S, T>> fieldDiffCollectorMap);

    IObjectDiffCollectorBuilder<S, T> putAllBindings(Collection<? extends NamedFieldDiffCollector<S, T>> namedFieldDiffCollectors);

}
