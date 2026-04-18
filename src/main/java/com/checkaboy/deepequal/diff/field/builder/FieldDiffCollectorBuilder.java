package com.checkaboy.deepequal.diff.field.builder;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.diff.collection.ICollectionDiffCollector;
import com.checkaboy.deepequal.diff.field.FieldDiffCollector;
import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;
import com.checkaboy.deepequal.diff.map.IMapDiffCollector;
import com.checkaboy.objectutils.container.Abstract4TypifiedContainer;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldDiffCollectorBuilder<SO, SV, TO, TV>
        extends Abstract4TypifiedContainer<SO, SV, TO, TV>
        implements IFieldDiffCollectorBuilder<SO, SV, TO, TV> {

    private Function<SO, SV> sourceExtractor = s -> null;
    private Function<TO, TV> targetExtractor = t -> null;
    private IFieldDiffCollector<SV, TV> diffCollector = (comparisonContext, diffNodeFactory, source, target, currentPath) -> null;

    protected FieldDiffCollectorBuilder(Class<SO> sourceObjectType, Class<SV> sourceValueType, Class<TO> targetObjectType, Class<TV> targetValueType) {
        super(sourceObjectType, sourceValueType, targetObjectType, targetValueType);
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
    public IFieldDiffCollector<SO, TO> build() {
        return new FieldDiffCollector<>(/*fieldName, */sourceExtractor, targetExtractor, diffCollector);
    }

    // =================================================================================================================

    public static <S, V> IFieldDiffCollector<S, S> oneObjectFieldDiffCollector(
            Function<S, V> extractor
    ) {
        return oneObjectFieldDiffCollector(extractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

    public static <S, V> IFieldDiffCollector<S, S> oneObjectFieldDiffCollector(
            Function<S, V> extractor,
            IFieldComparator<V, V> comparator
    ) {
        return new FieldDiffCollector<>(
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
            Function<SO, V> sourceExtractor,
            Function<TO, V> targetExtractor
    ) {
        return doubleObjectFieldDiffCollector(sourceExtractor, targetExtractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

    public static <SO, TO, V> IFieldDiffCollector<SO, TO> doubleObjectFieldDiffCollector(
            Function<SO, V> sourceExtractor,
            Function<TO, V> targetExtractor,
            IFieldComparator<V, V> comparator
    ) {
        return new FieldDiffCollector<>(
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
            Function<SO, SV> sourceExtractor,
            Function<TO, TV> targetExtractor
    ) {
        return doubleValueFieldDiffCollector(sourceExtractor, targetExtractor, (comparisonContext, source, target) -> Objects.equals(source, target));
    }

    public static <SO, SV, TO, TV> IFieldDiffCollector<SO, TO> doubleValueFieldDiffCollector(
            Function<SO, SV> sourceExtractor,
            Function<TO, TV> targetExtractor,
            IFieldComparator<SV, TV> comparator
    ) {
        return new FieldDiffCollector<>(
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

    public static <SO, SV, TO, TV> IFieldDiffCollector<SO, TO> wrap(
            Function<SO, Collection<SV>> sourceExtractor,
            Function<TO, Collection<TV>> targetExtractor,
            ICollectionDiffCollector<Collection<SV>, SV, Collection<TV>, TV> collectionDiffCollector
    ) {
        return new FieldDiffCollector<>(
                sourceExtractor,
                targetExtractor,
                collectionDiffCollector
        );
    }

    public static <SO, SK, SV, TO, TK, TV> IFieldDiffCollector<SO, TO> wrap(
            Function<SO, Map<SK, SV>> sourceExtractor,
            Function<TO, Map<TK, TV>> targetExtractor,
            IMapDiffCollector<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> mapDiffCollector
    ) {
        return new FieldDiffCollector<>(
                sourceExtractor,
                targetExtractor,
                mapDiffCollector
        );
    }

    // =================================================================================================================

    public static <SO, SV, TO, TV> FieldDiffCollectorBuilder<SO, SV, TO, TV> of(
            Class<SO> sourceObjectType, Class<SV> sourceValueType, Class<TO> targetObjectType, Class<TV> targetValueType
    ) {
        return new FieldDiffCollectorBuilder<>(sourceObjectType, sourceValueType, targetObjectType, targetValueType);
    }

    // =================================================================================================================

}
