package com.checkaboy.deepequal.comparator.strategy.collection;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
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
