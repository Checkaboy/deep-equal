package com.checkaboy.deepequal.diff.field.builder;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.field.FieldDiffCollector;
import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldDiffCollectorBuilder<SO, SV, TO, TV>
        extends AbstractBiTypifiedContainer<SV, TV>
        implements IFieldDiffCollectorBuilder<SO, SV, TO, TV> {

    private String fieldName = "";
    private Function<SO, SV> sourceExtractor = s -> null;
    private Function<TO, TV> targetExtractor = t -> null;
    private IFieldDiffCollector<SV, TV> diffCollector = (comparisonContext, diffNodeFactory, source, target, currentPath) -> null;

    public FieldDiffCollectorBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(sourceType, targetType);
    }

    public FieldDiffCollectorBuilder<SO, SV, TO, TV> setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    @Override
    public FieldDiffCollectorBuilder<SO, SV, TO, TV> setSourceExtractor(Function<SO, SV> extractor) {
        this.sourceExtractor = extractor;
        return this;
    }

    @Override
    public FieldDiffCollectorBuilder<SO, SV, TO, TV> setTargetExtractor(Function<TO, TV> extractor) {
        this.targetExtractor = extractor;
        return this;
    }

    @Override
    public FieldDiffCollectorBuilder<SO, SV, TO, TV> setDiffCollector(IFieldDiffCollector<SV, TV> diffCollector) {
        this.diffCollector = diffCollector;
        return this;
    }

    @Override
    public IDiffCollector<SO, TO> build() {
        return new FieldDiffCollector<>(fieldName, sourceExtractor, targetExtractor, diffCollector);
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
