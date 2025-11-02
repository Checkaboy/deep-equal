package com.checkaboy.deepequal.comparator.collection.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class IdentifiedCollectionComparisonStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionComparisonStrategy<SC, SV, TC, TV> {

    private final IFieldComparator<SV, TV> idComparator;

    public IdentifiedCollectionComparisonStrategy(IFieldComparator<SV, TV> idComparator) {
        this.idComparator = idComparator;
    }

    @Override
    public boolean compare(IComparisonContext comparisonContext, SC source, TC target, IFieldComparator<SV, TV> comparator) {
        for (SV sourceValue : source) {
            TV targetValue = findById(comparisonContext, sourceValue, target);
            if (targetValue == null || !comparator.compare(comparisonContext, sourceValue, targetValue)) return false;
        }

        return true;
    }

    // TODO move to object-utils module
    private TV findById(IComparisonContext comparisonContext, SV sourceValue, Collection<TV> target) {
        List<TV> found = new ArrayList<>();
        for (TV t : target) {
            if (idComparator.compare(comparisonContext, sourceValue, t))
                found.add(t);
        }
        if (found.size() == 1) return found.get(0);
        if (found.isEmpty()) return null;
        throw new IllegalStateException("Ambiguous identifier match");
    }

}
