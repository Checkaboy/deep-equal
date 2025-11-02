package com.checkaboy.deepequal.diff.strategy.collection;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class OrderedCollectionDiffCollectorStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends AbstractCollectionDiffCollectorStrategy<SC, SV, TC, TV>
        implements ICollectionDiffCollectorStrategy<SC, SV, TC, TV> {

    public OrderedCollectionDiffCollectorStrategy(Supplier<Collection<IDiffNode>> constructor, IFieldComparator<SV, TV> comparator) {
        super(constructor, comparator);
    }

    @Override
    public Collection<IDiffNode> collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SC source, TC target, String currentPath) {
        Iterator<SV> sourceIterator = source.iterator();
        Iterator<TV> targetIterator = target.iterator();

        Collection<IDiffNode> collection = constructor.get();

        int index = 0;
        while (sourceIterator.hasNext() || targetIterator.hasNext()) {
            SV sourceValue = sourceIterator.hasNext() ? sourceIterator.next() : null;
            TV targetValue = targetIterator.hasNext() ? targetIterator.next() : null;

            if (!comparator.compare(comparisonContext, sourceValue, targetValue))
                collection.add(diffNodeFactory.create(currentPath + "[" + index + "]", sourceValue, targetValue));

            index++;
        }

        return collection.isEmpty() ? null : collection;
    }

}
