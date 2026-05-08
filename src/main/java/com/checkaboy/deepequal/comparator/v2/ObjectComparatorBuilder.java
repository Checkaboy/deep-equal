package com.checkaboy.deepequal.comparator.v2;

import com.checkaboy.deepequal.comparator.IComparator;
import com.google.common.reflect.TypeToken;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class ObjectComparatorBuilder<S, T> {

    private final TypeToken<S> sourceType;
    private final TypeToken<T> targetType;
    private final ComparatorRegistry registry;
    private final Map<String, IComparator<S, T>> fields = new LinkedHashMap<>();

    public ObjectComparatorBuilder(TypeToken<S> sourceType, TypeToken<T> targetType, ComparatorRegistry registry) {
        this.sourceType = sourceType;
        this.targetType = targetType;
        this.registry = registry;
    }

    public <SV, TV> ObjectComparatorBuilder<S, T> field(
            String name,
            TypeToken<SV> sv,
            TypeToken<TV> tv,
            Function<S, SV> se,
            Function<T, TV> te
    ) {
        fields.put(name, new FieldComparator<>(sv, tv, se, te, null));
        return this;
    }

    public <SV, TV> ObjectComparatorBuilder<S, T> field(
            String name,
            TypeToken<SV> sv,
            TypeToken<TV> tv,
            Function<S, SV> se,
            Function<T, TV> te,
            IComparator<SV, TV> comparator
    ) {
        fields.put(name, new FieldComparator<>(sv, tv, se, te, comparator));
        return this;
    }

    public ObjectComparator<S, T> buildAndRegister() {
        ObjectComparator<S, T> comparator = new ObjectComparator<>(sourceType, targetType, fields);
        registry.register(sourceType, targetType, ctx -> comparator);
        return comparator;
    }

}
