package com.checkaboy.deepequal.diff.field;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldDiffCollector<SO, SV, TO, TV>
        implements IFieldDiffCollector<SO, TO> {

    private final String fieldName;// TODO delete after tests
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

}
