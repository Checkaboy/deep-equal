package com.checkaboy.deepequal.comparator;

import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.interf.IObjectComparator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public class ObjectComparator<O>
        extends HashMap<String, IFieldComparator<O>>
        implements IObjectComparator<O> {

    public ObjectComparator(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ObjectComparator(int initialCapacity) {
        super(initialCapacity);
    }

    public ObjectComparator() {
    }

    public ObjectComparator(Map<? extends String, ? extends IFieldComparator<O>> m) {
        super(m);
    }

    @Override
    public boolean equal(O first, O second) {
        //TODO Add logic to compare subclasses. If the first or second subobject represented has all fields equal
        // to NULL, and the other subobject is also equal to NULL. They are equal now.
        for (Entry<String, IFieldComparator<O>> entry : entrySet()) {
            if (!entry.getValue().equal(first, second))
                return false;
        }
        return true;
    }

    @Override
    public boolean fieldEqual(String fieldName, O first, O second) {
        IFieldComparator<O> fieldComparator = get(fieldName);
        if (fieldComparator == null) return true;
        return fieldComparator.equal(first, second);
    }

}
