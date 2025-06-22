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
        implements IObjectComparator<O> {

    private final Map<String, IFieldComparator<O>> fieldComparatorMap;

    public ObjectComparator() {
        this(new HashMap<>());
    }

    public ObjectComparator(Map<String, IFieldComparator<O>> fieldComparatorMap) {
        this.fieldComparatorMap = fieldComparatorMap;
    }

    @Override
    public boolean equal(O first, O second) {
        for (Entry<String, IFieldComparator<O>> entry : entrySet()) {
            if (!entry.getValue().equal(first, second))
                return false;
        }
        return true;
    }

    @Override
    public int size() {
        return fieldComparatorMap.size();
    }

    @Override
    public boolean isEmpty() {
        return fieldComparatorMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return fieldComparatorMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return fieldComparatorMap.containsValue(value);
    }

    @Override
    public IFieldComparator<O> get(Object key) {
        return fieldComparatorMap.get(key);
    }

    @Override
    public IFieldComparator<O> put(String fieldName, IFieldComparator<O> comparator) {
        return fieldComparatorMap.put(fieldName, comparator);
    }

    @Override
    public IFieldComparator<O> remove(Object key) {
        return fieldComparatorMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends IFieldComparator<O>> m) {
        fieldComparatorMap.putAll(m);
    }

    @Override
    public void clear() {
        fieldComparatorMap.clear();
    }

    @Override
    public Set<String> keySet() {
        return fieldComparatorMap.keySet();
    }

    @Override
    public Collection<IFieldComparator<O>> values() {
        return fieldComparatorMap.values();
    }

    @Override
    public Set<Entry<String, IFieldComparator<O>>> entrySet() {
        return fieldComparatorMap.entrySet();
    }

    @Override
    public boolean fieldEqual(String fieldName, O first, O second) {
        IFieldComparator<O> fieldComparator = fieldComparatorMap.get(fieldName);
        if (fieldComparator == null) return true;
        return fieldComparatorMap.get(fieldName).equal(first, second);
    }

}
