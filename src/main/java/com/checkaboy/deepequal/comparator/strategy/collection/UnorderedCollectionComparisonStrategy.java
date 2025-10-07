package com.checkaboy.deepequal.comparator.strategy.collection;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class UnorderedCollectionComparisonStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionComparisonStrategy<SC, SV, TC, TV> {

    @Override
    public boolean compare(IComparisonContext comparisonContext, SC source, TC target, IFieldComparator<SV, TV> comparator) {
        List<TV> remaining = new ArrayList<>(target);

        for (SV sourceValue : source) {
            Iterator<TV> remainingIterator = remaining.iterator();
            boolean matched = false;
            while (remainingIterator.hasNext()) {
                TV t = remainingIterator.next();
                if (comparator.compare(comparisonContext, sourceValue, t)) {
                    remainingIterator.remove();
                    matched = true;
                    break;
                }
            }
            if (!matched) return false;
        }

        return remaining.isEmpty();
    }

}
