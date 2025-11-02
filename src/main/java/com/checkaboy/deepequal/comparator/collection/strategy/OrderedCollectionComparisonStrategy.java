package com.checkaboy.deepequal.comparator.collection.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Taras Shaptala
 */
public class OrderedCollectionComparisonStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionComparisonStrategy<SC, SV, TC, TV> {

    @Override
    public boolean compare(IComparisonContext comparisonContext, SC source, TC target, IFieldComparator<SV, TV> comparator) {
        Iterator<SV> sourceIterator = source.iterator();
        Iterator<TV> targetIterator = target.iterator();

        while (sourceIterator.hasNext() && targetIterator.hasNext()) {
            if (!comparator.compare(comparisonContext, sourceIterator.next(), targetIterator.next()))
                return false;
        }

        return true;
    }

}
