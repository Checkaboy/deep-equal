package com.checkaboy.deepequal.dsl;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.field.FieldComparator;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.field.FieldDiffCollector;
import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldRule<SO, SV, TO, TV>  {

    private final String name;
    private final Function<SO, SV> sourceExtractor;
    private final Function<TO, TV> targetExtractor;

    public FieldRule(String name, Function<SO, SV> sourceExtractor, Function<TO, TV> targetExtractor) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.sourceExtractor = Objects.requireNonNull(sourceExtractor, "sourceExtractor must not be null");
        this.targetExtractor = Objects.requireNonNull(targetExtractor, "targetExtractor must not be null");
    }

    public NamedFieldComparator<SO, TO> comparator(IComparator<SV, TV> comparator) {
        Objects.requireNonNull(comparator, "comparator must not be null");
        IFieldComparator<SV, TV> fieldComparator = (comparisonContext, source, target) -> comparator.compare(comparisonContext, source, target);
        return new NamedFieldComparator<>(name, new FieldComparator<>(sourceExtractor, targetExtractor, fieldComparator));
    }

    public NamedFieldComparator<SO, TO> comparator() {
        return comparator((comparisonContext, source, target) -> Objects.equals(source, target));
    }

    public NamedFieldDiffCollector<SO, TO> diffCollector(IDiffCollector<SV, TV> diffCollector) {
        Objects.requireNonNull(diffCollector, "diffCollector must not be null");
        IFieldDiffCollector<SV, TV> fieldDiffCollector =
                (comparisonContext, diffNodeFactory, source, target, currentPath) ->
                        diffCollector.collect(comparisonContext, diffNodeFactory, source, target, currentPath);
        return new NamedFieldDiffCollector<>(name, new FieldDiffCollector<>(sourceExtractor, targetExtractor, fieldDiffCollector));
    }

    public NamedFieldDiffCollector<SO, TO> diffCollector() {
        return diffCollector(
                (comparisonContext, diffNodeFactory, source, target, currentPath) ->
                        Objects.equals(source, target) ? null : diffNodeFactory.create(currentPath, source, target)
        );
    }

}
