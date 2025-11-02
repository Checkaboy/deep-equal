package com.checkaboy.deepequal.comparator.collection.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public class UniqueElementsCollectionComparisonStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionComparisonStrategy<SC, SV, TC, TV> {

    @Override
    public boolean compare(IComparisonContext comparisonContext, SC source, TC target, IFieldComparator<SV, TV> comparator) {
        Set<SV> uniqueSource = new HashSet<>(source);
        Set<TV> uniqueTarget = new HashSet<>(target);

        if (uniqueSource.size() != uniqueTarget.size()) return false;

        for (SV sourceValue : uniqueSource) {
            boolean matched = false;
            for (TV targetValue : uniqueTarget) {
                if (comparator.compare(comparisonContext, sourceValue, targetValue)) {
                    matched = true;
                    break;
                }
            }
            if (!matched) return false;
        }

        return true;
    }

}
