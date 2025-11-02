package com.checkaboy.deepequal.diff.model;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.model.interf.IMapDiffCollector;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class MapDiffCollector<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        implements IMapDiffCollector<SM, SK, SV, TM, TK, TV> {

//    protected final IKeyMatchingStrategy<SK, SV, TK, TV> keyMatchingStrategy;
    protected final Supplier<Collection<IDiffNode>> constructor;
    protected final IFieldComparator<SV, TV> comparator;

    public MapDiffCollector(/*IKeyMatchingStrategy<SK, SV, TK, TV> keyMatchingStrategy, */Supplier<Collection<IDiffNode>> constructor, IFieldComparator<SV, TV> comparator) {
//        this.keyMatchingStrategy = keyMatchingStrategy;
        this.constructor = constructor;
        this.comparator = comparator;
    }

    @Override
    public IDiffNode collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, SM source, TM target, String currentPath) {
//        if (source == null && target == null) return null;
//        IDiffNode node = diffNodeFactory.create(currentPath, source, target);
//
//        if (source == null || target == null) return node;

//        Collection<IDiffNode> diffs = constructor.get();
//        for (Map.Entry<SK, SV> entry : source.entrySet()) {
//            TV targetValue = keyMatchingStrategy.findMatchingValue(comparisonContext, entry.getKey(), entry.getValue(), target);
//            if (!comparator.compare(comparisonContext, entry.getValue(), targetValue))
//                diffs.add(diffNodeFactory.create(currentPath + "[" + entry.getKey() + "]", entry.getValue(), targetValue));
//        }

//        if (diffs.isEmpty()) return null;
//        else {
//            node.addChildren(diffs);
//            return node;
//        }
        return null;
    }

}
