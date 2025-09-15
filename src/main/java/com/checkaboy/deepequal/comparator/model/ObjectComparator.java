package com.checkaboy.deepequal.comparator.model;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.model.interf.IObjectComparator;

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
    public boolean equal(S source, T target) {
        //TODO Add logic to compare subclasses. If the first or second sub object represented has all fields equal
        // to NULL, and the other sub object is also equal to NULL. They are equal now.
        for (Entry<String, IFieldComparator<S, T>> entry : entrySet()) {
            if (!entry.getValue().equal(source, target))
                return false;
        }
        return true;
    }

    @Override
    public boolean fieldEqual(String fieldName, S source, T target) {
        IFieldComparator<S, T> fieldComparator = get(fieldName);
        if (fieldComparator == null) return true;
        return fieldComparator.equal(source, target);
    }

}
