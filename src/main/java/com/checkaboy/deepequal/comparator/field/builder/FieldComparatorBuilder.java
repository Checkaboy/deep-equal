package com.checkaboy.deepequal.comparator.field.builder;

import com.checkaboy.deepequal.comparator.field.FieldComparator;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldComparatorBuilder<SO, SV, TO, TV>
        extends AbstractBiTypifiedContainer<SV, TV>
        implements IFieldComparatorBuilder<SO, SV, TO, TV> {

    private Function<SO, SV> sourceExtractor = o -> null;
    private Function<TO, TV> targetExtractor = o -> null;
    private IFieldComparator<SV, TV> comparator = (comparisonContext, source, target) -> Objects.equals(source, target);

    public FieldComparatorBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public FieldComparatorBuilder<SO, SV, TO, TV> setSourceExtractor(Function<SO, SV> extractor) {
        this.sourceExtractor = extractor;
        return this;
    }

    @Override
    public FieldComparatorBuilder<SO, SV, TO, TV> setTargetExtractor(Function<TO, TV> extractor) {
        this.targetExtractor = extractor;
        return this;
    }

    @Override
    public FieldComparatorBuilder<SO, SV, TO, TV> setComparator(IFieldComparator<SV, TV> comparator) {
        this.comparator = comparator;
        return this;
    }

    @Override
    public IFieldComparator<SO, TO> build() {
        return new FieldComparator<>(sourceExtractor, targetExtractor, comparator);
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
