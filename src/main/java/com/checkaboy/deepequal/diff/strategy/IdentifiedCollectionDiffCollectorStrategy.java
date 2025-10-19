package com.checkaboy.deepequal.diff.strategy;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.strategy.collection.ICollectionComparisonStrategy;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.model.interf.IFieldDiffCollector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class IdentifiedCollectionDiffCollectorStrategy<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionDiffCollectorStrategy<SC, SV, TC, TV> {

    private final IFieldComparator<SV, TV> idComparator;
    private final Supplier<Collection<IDiffNode>> constructor;

    public IdentifiedCollectionDiffCollectorStrategy(IFieldComparator<SV, TV> idComparator, Supplier<Collection<IDiffNode>> constructor) {
        this.idComparator = idComparator;
        this.constructor = constructor;
    }

    @Override
    public Collection<IDiffNode> collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SC source, TC target, String currentPath, IFieldDiffCollector<SV, TV> diffCollector) {
        Collection<IDiffNode> collection = constructor.get();

        int index = 0;
        for (SV sourceValue : source) {
            TV targetValue = findById(comparisonContext, sourceValue, target);
            IDiffNode diffNode = diffCollector.collect(comparisonContext, diffNodeFactory, sourceValue, targetValue, currentPath + "[" + index + "]");
            if(diffNode != null) collection.add(diffNode);
            index++;
        }

        return collection.isEmpty() ? null : collection;
    }

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
