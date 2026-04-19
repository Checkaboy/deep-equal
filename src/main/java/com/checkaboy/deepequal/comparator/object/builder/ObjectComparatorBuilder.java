package com.checkaboy.deepequal.comparator.object.builder;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.field.FieldComparator;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.comparator.object.IObjectComparator;
import com.checkaboy.deepequal.comparator.object.ObjectComparator;
import com.checkaboy.deepequal.dsl.NamedFieldComparator;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class ObjectComparatorBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IObjectComparatorBuilder<S, T> {

    private final Map<String, IFieldComparator<S, T>> fieldComparatorMap = new LinkedHashMap<>();

    protected ObjectComparatorBuilder(Class<S> sourceClass, Class<T> targetClass) {
        super(sourceClass, targetClass);
    }

    @Override
    public ObjectComparatorBuilder<S, T> set(Map<String, ? extends IFieldComparator<S, T>> fieldComparatorMap) {
        Objects.requireNonNull(fieldComparatorMap, "fieldComparatorMap must not be null");
        this.fieldComparatorMap.clear();
        return putAll(fieldComparatorMap);
    }

    @Override
    public ObjectComparatorBuilder<S, T> put(String fieldName, IFieldComparator<S, T> fieldComparator) {
        fieldComparatorMap.put(fieldName, Objects.requireNonNull(fieldComparator, "fieldComparator must not be null"));
        return this;
    }

    @Override
    public ObjectComparatorBuilder<S, T> put(NamedFieldComparator<S, T> namedFieldComparator) {
        Objects.requireNonNull(namedFieldComparator, "namedFieldComparator must not be null");
        return put(namedFieldComparator.getName(), namedFieldComparator.getComparator());
    }

    @Override
    public <SV, TV> ObjectComparatorBuilder<S, T> put(
            String fieldName,
            Function<S, SV> sourceExtractor,
            Function<T, TV> targetExtractor,
            IComparator<SV, TV> comparator
    ) {
        fieldComparatorMap.put(fieldName, createFieldComparator(sourceExtractor, targetExtractor, comparator));
        return this;
    }

    @Override
    public <V> ObjectComparatorBuilder<S, T> put(
            String fieldName,
            Function<S, V> sourceExtractor,
            Function<T, V> targetExtractor
    ) {
        return put(fieldName, sourceExtractor, targetExtractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

    @Override
    public ObjectComparatorBuilder<S, T> putAll(Map<String, ? extends IFieldComparator<S, T>> fieldComparatorMap) {
        Objects.requireNonNull(fieldComparatorMap, "fieldComparatorMap must not be null");
        this.fieldComparatorMap.putAll(fieldComparatorMap);
        return this;
    }

    @Override
    public ObjectComparatorBuilder<S, T> putAllBindings(Collection<? extends NamedFieldComparator<S, T>> namedFieldComparators) {
        Objects.requireNonNull(namedFieldComparators, "namedFieldComparators must not be null");
        for (NamedFieldComparator<S, T> namedFieldComparator : namedFieldComparators) {
            put(namedFieldComparator);
        }
        return this;
    }

    @Override
    public IObjectComparator<S, T> build() {
        return new ObjectComparator<>(fieldComparatorMap);
    }

    protected <SV, TV> IFieldComparator<S, T> createFieldComparator(
            Function<S, SV> sourceExtractor,
            Function<T, TV> targetExtractor,
            IComparator<SV, TV> comparator
    ) {
        Objects.requireNonNull(sourceExtractor, "sourceExtractor must not be null");
        Objects.requireNonNull(targetExtractor, "targetExtractor must not be null");
        Objects.requireNonNull(comparator, "comparator must not be null");

        if (comparator instanceof IFieldComparator<?, ?>) {
            return new FieldComparator<>(sourceExtractor, targetExtractor, castFieldComparator(comparator));
        }

        return new FieldComparator<>(sourceExtractor, targetExtractor, comparator::compare);
    }

    @SuppressWarnings("unchecked")
    private static <SV, TV> IFieldComparator<SV, TV> castFieldComparator(IComparator<SV, TV> comparator) {
        return (IFieldComparator<SV, TV>) comparator;
    }

    public static <S, T> ObjectComparatorBuilder<S, T> of(Class<S> sourceClass, Class<T> targetClass) {
        return new ObjectComparatorBuilder<>(sourceClass, targetClass);
    }

}
