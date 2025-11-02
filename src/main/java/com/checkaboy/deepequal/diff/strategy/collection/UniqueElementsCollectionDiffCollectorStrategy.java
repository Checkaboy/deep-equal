package com.checkaboy.deepequal.diff.strategy.collection;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class UniqueElementsCollectionDiffCollectorStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends AbstractCollectionDiffCollectorStrategy<SC, SV, TC, TV>
        implements ICollectionDiffCollectorStrategy<SC, SV, TC, TV> {

    protected UniqueElementsCollectionDiffCollectorStrategy(Supplier<Collection<IDiffNode>> constructor, IFieldComparator<SV, TV> comparator) {
        super(constructor, comparator);
    }

    @Override
    public Collection<IDiffNode> collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SC source, TC target, String currentPath) {
        Set<SV> uniqueSource = new HashSet<>(source);
        Set<TV> uniqueTarget = new HashSet<>(target);

        Collection<IDiffNode> collection = constructor.get();

        int index = 0;
        for (SV sourceValue : uniqueSource) {
            boolean matched = false;
            for (TV targetValue : uniqueTarget) {
                if (comparator.compare(comparisonContext, sourceValue, targetValue)) {
                    matched = true;
                    break;
                }
            }
            if (!matched) collection.add(diffNodeFactory.create(currentPath + "[" + index + "]", source, null));

            index++;
        }

        return collection.isEmpty() ? null : collection;
    }

}
