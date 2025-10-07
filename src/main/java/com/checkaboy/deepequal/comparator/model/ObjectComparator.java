package com.checkaboy.deepequal.comparator.model;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.model.interf.IObjectComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectComparator<S, T>
        extends HashMap<String, IFieldComparator<S, T>>
        implements IObjectComparator<S, T> {

    public ObjectComparator(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ObjectComparator(int initialCapacity) {
        super(initialCapacity);
    }

    public ObjectComparator() {
    }

    public ObjectComparator(Map<? extends String, ? extends IFieldComparator<S, T>> m) {
        super(m);
    }

    @Override
    public boolean compare(IComparisonContext comparisonContext, S source, T target) {
        if (source == null && target == null) return true;
        if (source == null || target == null) return false;

        if (comparisonContext != null && !comparisonContext.enter(source, target)) return true;

        for (Entry<String, IFieldComparator<S, T>> entry : entrySet()) {
            if (!entry.getValue().compare(comparisonContext, source, target))
                return false;
        }

        return true;
    }

    @Override
    public boolean fieldEqual(IComparisonContext comparisonContext, String fieldName, S source, T target) {
        IFieldComparator<S, T> fieldComparator = get(fieldName);
        if (fieldComparator == null) return true;
        if (comparisonContext != null && !comparisonContext.enter(source, target)) return true;
        return fieldComparator.compare(comparisonContext, source, target);
    }

}
