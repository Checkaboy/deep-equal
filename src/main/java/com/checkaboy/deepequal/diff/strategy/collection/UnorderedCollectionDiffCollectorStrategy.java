package com.checkaboy.deepequal.diff.strategy.collection;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class UnorderedCollectionDiffCollectorStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends AbstractCollectionDiffCollectorStrategy<SC, SV, TC, TV>
        implements ICollectionDiffCollectorStrategy<SC, SV, TC, TV> {

    protected UnorderedCollectionDiffCollectorStrategy(Supplier<Collection<IDiffNode>> constructor, IFieldComparator<SV, TV> comparator) {
        super(constructor, comparator);
    }

    @Override
    public Collection<IDiffNode> collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SC source, TC target, String currentPath) {
        List<TV> remaining = new ArrayList<>(target);

        Collection<IDiffNode> collection = constructor.get();

        int index = 0;
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
            if (!matched) collection.add(diffNodeFactory.create(currentPath + "[" + index + "]", source, null));

            index++;
        }

        return collection.isEmpty() ? null : collection;
    }

}
