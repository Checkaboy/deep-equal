package com.checkaboy.deepequal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractObjectEq<O>
        implements IFieldEq<O>, IObjectEq<O> {

    private final Map<String, IFieldEq<O>> fieldEqMap = new HashMap<>();

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
    public IFieldEq<O> get(Object key) {
        return fieldEqMap.get(key);
    }

    @Override
    public IFieldEq<O> put(String fieldName, IFieldEq<O> eq) {
        return fieldEqMap.put(fieldName, eq);
    }

    @Override
    public IFieldEq<O> remove(Object key) {
        return fieldEqMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends IFieldEq<O>> m) {
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
    public Collection<IFieldEq<O>> values() {
        return fieldEqMap.values();
    }

    @Override
    public Set<Entry<String, IFieldEq<O>>> entrySet() {
        return fieldEqMap.entrySet();
    }

}
