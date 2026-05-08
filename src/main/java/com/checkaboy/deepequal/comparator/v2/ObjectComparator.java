package com.checkaboy.deepequal.comparator.v2;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.google.common.reflect.TypeToken;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectComparator<S, T> implements IComparator<S, T> {

    private final TypeToken<S> sourceType;
    private final TypeToken<T> targetType;
    private final Map<String, IComparator<S, T>> fields;

    public ObjectComparator(TypeToken<S> sourceType, TypeToken<T> targetType, Map<String, IComparator<S, T>> fields) {
        this.sourceType = sourceType;
        this.targetType = targetType;
        this.fields = Collections.unmodifiableMap(new LinkedHashMap<>(fields));
    }

    @Override
    public boolean compare(IComparisonContext ctx, S source, T target) {
        if (source == null && target == null) return true;
        if (source == null || target == null) return false;
        if (!ctx.enter(source, target)) return true;
        for (IComparator<S, T> comparator : fields.values()) {
            if (!comparator.compare(ctx, source, target)) return false;
        }
        return true;
    }

    public TypeToken<S> sourceType() {
        return sourceType;
    }

    public TypeToken<T> targetType() {
        return targetType;
    }

}

