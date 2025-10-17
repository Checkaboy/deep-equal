package com.checkaboy.deepequal.diff.model;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.model.interf.IFieldDiffCollector;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldDiffCollector<SO, SV, TO, TV>
        implements IFieldDiffCollector<SO, TO> {

    private final String fieldName;
    private final Function<SO, SV> sourceExtractor;
    private final Function<TO, TV> targetExtractor;
    private final IFieldDiffCollector<SV, TV> diffCollector;

    public FieldDiffCollector(
            String fieldName,
            Function<SO, SV> sourceExtractor,
            Function<TO, TV> targetExtractor,
            IFieldDiffCollector<SV, TV> diffCollector
    ) {
        this.fieldName = fieldName;
        this.sourceExtractor = sourceExtractor;
        this.targetExtractor = targetExtractor;
        this.diffCollector = diffCollector;
    }

    @Override
    public IDiffNode collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SO source, TO target, String currentPath) {
        return diffCollector.collect(
                comparisonContext,
                diffNodeFactory,
                sourceExtractor.apply(source),
                targetExtractor.apply(target),
                currentPath + "." + fieldName
        );
    }

    // =================================================================================================================

    public static <S, V> IFieldDiffCollector<S, S> oneObjectFieldDiffCollector(
            String fieldName,
            Function<S, V> extractor
    ) {
        return oneObjectFieldDiffCollector(fieldName, extractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

    public static <S, V> IFieldDiffCollector<S, S> oneObjectFieldDiffCollector(
            String fieldName,
            Function<S, V> extractor,
            IFieldComparator<V, V> comparator
    ) {
        return new FieldDiffCollector<>(
                fieldName,
                extractor,
                extractor,
                (comparisonContext, diffNodeFactory, source, target, currentPath) -> {
                    if (!comparator.compare(comparisonContext, source, target))
                        return diffNodeFactory.create(currentPath, source, target);
                    else return null;
                }
        );
    }

    // =================================================================================================================

    public static <SO, TO, V> IFieldDiffCollector<SO, TO> doubleObjectFieldDiffCollector(
            String fieldName,
            Function<SO, V> sourceExtractor,
            Function<TO, V> targetExtractor
    ) {
        return doubleObjectFieldDiffCollector(fieldName, sourceExtractor, targetExtractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

    public static <SO, TO, V> IFieldDiffCollector<SO, TO> doubleObjectFieldDiffCollector(
            String fieldName,
            Function<SO, V> sourceExtractor,
            Function<TO, V> targetExtractor,
            IFieldComparator<V, V> comparator
    ) {
        return new FieldDiffCollector<>(
                fieldName,
                sourceExtractor,
                targetExtractor,
                (comparisonContext, diffNodeFactory, source, target, currentPath) -> {
                    if (!comparator.compare(comparisonContext, source, target))
                        return diffNodeFactory.create(currentPath, source, target);
                    else return null;
                }
        );
    }

    // =================================================================================================================

    public static <SO, SV, TO, TV> IFieldDiffCollector<SO, TO> doubleValueFieldDiffCollector(
            String fieldName,
            Function<SO, SV> sourceExtractor,
            Function<TO, TV> targetExtractor
    ) {
        return doubleValueFieldDiffCollector(fieldName, sourceExtractor, targetExtractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

    public static <SO, SV, TO, TV> IFieldDiffCollector<SO, TO> doubleValueFieldDiffCollector(
            String fieldName,
            Function<SO, SV> sourceExtractor,
            Function<TO, TV> targetExtractor,
            IFieldComparator<SV, TV> comparator
    ) {
        return new FieldDiffCollector<>(
                fieldName,
                sourceExtractor,
                targetExtractor,
                (comparisonContext, diffNodeFactory, source, target, currentPath) -> {
                    if (!comparator.compare(comparisonContext, source, target))
                        return diffNodeFactory.create(currentPath, source, target);
                    else return null;
                }
        );
    }

    // =================================================================================================================

}
