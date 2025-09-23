package com.checkaboy.deepequal.comparator.model;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldComparator<SO, SV, TO, TV>
        implements IFieldComparator<SO, TO> {

    private final Function<SO, SV> sourceExtractor;
    private final Function<TO, TV> targetExtractor;
    private final IFieldComparator<SV, TV> comparator;

    public FieldComparator(Function<SO, SV> sourceExtractor, Function<TO, TV> targetExtractor, IFieldComparator<SV, TV> comparator) {
        this.sourceExtractor = sourceExtractor;
        this.targetExtractor = targetExtractor;
        this.comparator = comparator;
    }

    @Override
    public boolean compare(IComparisonContext comparisonContext, SO first, TO second) {
        return comparator.compare(comparisonContext, sourceExtractor.apply(first), targetExtractor.apply(second));
    }

    public static <O, V> IFieldComparator<O, O> simpleFieldComparator(Function<O, V> extractor) {
        return new FieldComparator<>(extractor, extractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

    public static <S, T, V> IFieldComparator<S, T> simpleFieldComparator(Function<S, V> sourceExtractor, Function<T, V> targetExtractor) {
        return new FieldComparator<>(sourceExtractor, targetExtractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

}
