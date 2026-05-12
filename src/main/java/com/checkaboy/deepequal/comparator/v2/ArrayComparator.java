package com.checkaboy.deepequal.comparator.v2;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.google.common.reflect.TypeToken;

public class ArrayComparator<S, T>
        implements IComparator<S[], T[]> {

    private final TypeToken<S> sourceElementType;
    private final TypeToken<T> targetElementType;

    public ArrayComparator(TypeToken<S> sourceElementType, TypeToken<T> targetElementType) {
        this.sourceElementType = sourceElementType;
        this.targetElementType = targetElementType;
    }

    @Override
    public boolean compare(IComparisonContext ctx, S[] source, T[] target) {
        if (source == null && target == null) return true;
        if (source == null || target == null || source.length != target.length) return false;
        IComparator<S, T> elementComparator = ctx.registry().resolve(sourceElementType, targetElementType, ctx);
        for (int i = 0; i < source.length; i++) {
            if (!elementComparator.compare(ctx, source[i], target[i])) return false;
        }
        return true;
    }

}
