package com.checkaboy.deepequal.model.single;

import com.checkaboy.deepequal.model.single.interf.IComparator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractMapObjectComparator<O>
        implements Map<String, IComparator<O>> {

    private final Map<String, IComparator<O>> fieldEqMap;

    public AbstractMapObjectComparator() {
        this(new HashMap<>());
    }

    public AbstractMapObjectComparator(Map<String, IComparator<O>> fieldComparatorMap) {
        this.fieldEqMap = fieldComparatorMap;
    }

    @Override
    public int size() {
        return fieldEqMap.size();
    }

    @Override
    public boolean isEmpty() {
        return fieldEqMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return fieldEqMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return fieldEqMap.containsValue(value);
    }

    @Override
    public IComparator<O> get(Object key) {
        return fieldEqMap.get(key);
    }

    @Override
    public IComparator<O> put(String fieldName, IComparator<O> eq) {
        return fieldEqMap.put(fieldName, eq);
    }

    @Override
    public IComparator<O> remove(Object key) {
        return fieldEqMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends IComparator<O>> m) {
        fieldEqMap.putAll(m);
    }

    @Override
    public void clear() {
        fieldEqMap.clear();
    }

    @Override
    public Set<String> keySet() {
        return fieldEqMap.keySet();
    }

    @Override
    public Collection<IComparator<O>> values() {
        return fieldEqMap.values();
    }

    @Override
    public Set<Entry<String, IComparator<O>>> entrySet() {
        return fieldEqMap.entrySet();
    }

}
