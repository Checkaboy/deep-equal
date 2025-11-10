package com.checkaboy.deepequal.diff.collection.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class IdentifiedCollectionDiffCollectionStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends AbstractCollectionDiffCollectionStrategy<SC, SV, TC, TV>
        implements ICollectionDiffCollectionStrategy<SC, SV, TC, TV> {

    private final IFieldComparator<SV, TV> idComparator;

    public IdentifiedCollectionDiffCollectionStrategy(Supplier<Collection<IDiffNode>> constructor, IFieldComparator<SV, TV> comparator, IFieldComparator<SV, TV> idComparator) {
        super(constructor, comparator);
        this.idComparator = idComparator;
    }

    @Override
    public Collection<IDiffNode> collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SC source, TC target, String currentPath) {
        Collection<IDiffNode> collection = constructor.get();

        int index = 0;
        for (SV sourceValue : source) {
            TV targetValue = findById(comparisonContext, sourceValue, target);
            if (!comparator.compare(comparisonContext, sourceValue, targetValue))
                collection.add(diffNodeFactory.create(currentPath + "[" + index + "]", sourceValue, targetValue));

            index++;
        }

        return collection.isEmpty() ? null : collection;
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
