package com.checkaboy.deepequal.diff.object.builder;

import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.field.FieldDiffCollector;
import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;
import com.checkaboy.deepequal.diff.object.IObjectDiffCollector;
import com.checkaboy.deepequal.diff.object.ObjectDiffCollector;
import com.checkaboy.deepequal.dsl.NamedFieldDiffCollector;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class ObjectDiffCollectorBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IObjectDiffCollectorBuilder<S, T> {

    private final Map<String, IFieldDiffCollector<S, T>> fieldDiffCollectorMap = new LinkedHashMap<>();

    protected ObjectDiffCollectorBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public ObjectDiffCollectorBuilder<S, T> set(Map<String, ? extends IFieldDiffCollector<S, T>> fieldDiffCollectorMap) {
        Objects.requireNonNull(fieldDiffCollectorMap, "fieldDiffCollectorMap must not be null");
        this.fieldDiffCollectorMap.clear();
        return putAll(fieldDiffCollectorMap);
    }

    @Override
    public ObjectDiffCollectorBuilder<S, T> put(String fieldName, IFieldDiffCollector<S, T> fieldDiffCollector) {
        fieldDiffCollectorMap.put(fieldName, Objects.requireNonNull(fieldDiffCollector, "fieldDiffCollector must not be null"));
        return this;
    }

    @Override
    public ObjectDiffCollectorBuilder<S, T> put(NamedFieldDiffCollector<S, T> namedFieldDiffCollector) {
        Objects.requireNonNull(namedFieldDiffCollector, "namedFieldDiffCollector must not be null");
        return put(namedFieldDiffCollector.getName(), namedFieldDiffCollector.getDiffCollector());
    }

    @Override
    public <SV, TV> ObjectDiffCollectorBuilder<S, T> put(
            String fieldName,
            Function<S, SV> sourceExtractor,
            Function<T, TV> targetExtractor,
            IDiffCollector<SV, TV> diffCollector
    ) {
        fieldDiffCollectorMap.put(fieldName, createFieldDiffCollector(sourceExtractor, targetExtractor, diffCollector));
        return this;
    }

    @Override
    public <V> ObjectDiffCollectorBuilder<S, T> put(
            String fieldName,
            Function<S, V> sourceExtractor,
            Function<T, V> targetExtractor
    ) {
        return put(
                fieldName,
                sourceExtractor,
                targetExtractor,
                (comparisonContext, diffNodeFactory, source, target, currentPath) ->
                        Objects.equals(source, target) ? null : diffNodeFactory.create(currentPath, source, target)
        );
    }

    @Override
    public ObjectDiffCollectorBuilder<S, T> putAll(Map<String, ? extends IFieldDiffCollector<S, T>> fieldDiffCollectorMap) {
        Objects.requireNonNull(fieldDiffCollectorMap, "fieldDiffCollectorMap must not be null");
        this.fieldDiffCollectorMap.putAll(fieldDiffCollectorMap);
        return this;
    }

    @Override
    public ObjectDiffCollectorBuilder<S, T> putAllBindings(Collection<? extends NamedFieldDiffCollector<S, T>> namedFieldDiffCollectors) {
        Objects.requireNonNull(namedFieldDiffCollectors, "namedFieldDiffCollectors must not be null");
        for (NamedFieldDiffCollector<S, T> namedFieldDiffCollector : namedFieldDiffCollectors) {
            put(namedFieldDiffCollector);
        }
        return this;
    }

    @Override
    public IObjectDiffCollector<S, T> build() {
        return new ObjectDiffCollector<>(fieldDiffCollectorMap);
    }

    protected <SV, TV> IFieldDiffCollector<S, T> createFieldDiffCollector(
            Function<S, SV> sourceExtractor,
            Function<T, TV> targetExtractor,
            IDiffCollector<SV, TV> diffCollector
    ) {
        Objects.requireNonNull(sourceExtractor, "sourceExtractor must not be null");
        Objects.requireNonNull(targetExtractor, "targetExtractor must not be null");
        Objects.requireNonNull(diffCollector, "diffCollector must not be null");

        if (diffCollector instanceof IFieldDiffCollector<?, ?>) {
            return new FieldDiffCollector<>(sourceExtractor, targetExtractor, castFieldDiffCollector(diffCollector));
        }

        return new FieldDiffCollector<>(sourceExtractor, targetExtractor, diffCollector::collect);
    }

    @SuppressWarnings("unchecked")
    private static <SV, TV> IFieldDiffCollector<SV, TV> castFieldDiffCollector(IDiffCollector<SV, TV> diffCollector) {
        return (IFieldDiffCollector<SV, TV>) diffCollector;
    }

    public static <S, T> ObjectDiffCollectorBuilder<S, T> of(Class<S> sourceType, Class<T> targetType) {
        return new ObjectDiffCollectorBuilder<>(sourceType, targetType);
    }

}
