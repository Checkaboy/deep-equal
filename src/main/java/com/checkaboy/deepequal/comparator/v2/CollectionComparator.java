package com.checkaboy.deepequal.comparator.v2;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.google.common.reflect.TypeToken;

import java.util.Collection;
import java.util.Iterator;

public class CollectionComparator<S, T>
        implements IComparator<Collection<S>, Collection<T>> {

    private final TypeToken<S> sourceElementType;
    private final TypeToken<T> targetElementType;

    public CollectionComparator(TypeToken<S> sourceElementType, TypeToken<T> targetElementType) {
        this.sourceElementType = sourceElementType;
        this.targetElementType = targetElementType;
    }

    @Override
    public boolean compare(IComparisonContext ctx, Collection<S> source, Collection<T> target) {
        if (source == null && target == null) return true;
        if (source == null || target == null || source.size() != target.size()) return false;
        ComparisonContext context = (ComparisonContext) ctx;
        IComparator<S, T> elementComparator = context.registry().resolve(sourceElementType, targetElementType, context);
        Iterator<S> left = source.iterator();
        Iterator<T> right = target.iterator();
        while (left.hasNext() && right.hasNext()) {
            if (!elementComparator.compare(ctx, left.next(), right.next())) return false;
        }
        return true;
    }

}
