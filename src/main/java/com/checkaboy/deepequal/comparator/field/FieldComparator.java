package com.checkaboy.deepequal.comparator.field;

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

}
