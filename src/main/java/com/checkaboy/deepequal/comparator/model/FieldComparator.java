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

    // =================================================================================================================

    public static <S, V> IFieldComparator<S, S> oneObjectFieldComparator(
            Function<S, V> extractor
    ) {
        return oneObjectFieldComparator(extractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

    public static <S, V> IFieldComparator<S, S> oneObjectFieldComparator(
            Function<S, V> extractor,
            IFieldComparator<V, V> comparator
    ) {
        return new FieldComparator<>(
                extractor,
                extractor,
                comparator
        );
    }

    // =================================================================================================================

    public static <SO, TO, V> IFieldComparator<SO, TO> doubleObjectFieldComparator(
            Function<SO, V> sourceExtractor,
            Function<TO, V> targetExtractor
    ) {
        return doubleObjectFieldComparator(sourceExtractor, targetExtractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

    public static <SO, TO, V> IFieldComparator<SO, TO> doubleObjectFieldComparator(
            Function<SO, V> sourceExtractor,
            Function<TO, V> targetExtractor,
            IFieldComparator<V, V> comparator
    ) {
        return new FieldComparator<>(
                sourceExtractor,
                targetExtractor,
                comparator
        );
    }

    // =================================================================================================================

    public static <SO, SV, TO, TV> IFieldComparator<SO, TO> doubleValueFieldDiffCollector(
            Function<SO, SV> sourceExtractor,
            Function<TO, TV> targetExtractor
    ) {
        return doubleValueFieldDiffCollector(sourceExtractor, targetExtractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

    public static <SO, SV, TO, TV> IFieldComparator<SO, TO> doubleValueFieldDiffCollector(
            Function<SO, SV> sourceExtractor,
            Function<TO, TV> targetExtractor,
            IFieldComparator<SV, TV> comparator
    ) {
        return new FieldComparator<>(
                sourceExtractor,
                targetExtractor,
                comparator
        );
    }

    // =================================================================================================================


}
