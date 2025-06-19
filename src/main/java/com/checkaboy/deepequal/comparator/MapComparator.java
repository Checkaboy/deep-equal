package com.checkaboy.deepequal.comparator;

import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.interf.IMapComparator;
import com.checkaboy.deepequal.factory.IMapFactory;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class MapComparator<M extends Map<K, V>, K, V>
        implements IMapComparator<M, K, V> {

    protected final IMapFactory<M, K, V> mapFactory;
    protected final IFieldComparator<V> comparator;

    public MapComparator(IMapFactory<M, K, V> mapFactory, IFieldComparator<V> comparator) {
        this.mapFactory = mapFactory;
        this.comparator = comparator;
    }

    @Override
    public boolean equal(M first, M second) {
        if (first != null && second != null) {
            if (first.size() != second.size())
                return false;

            for (Map.Entry<K, V> firstEntry : first.entrySet()) {
                V secondValue = second.get(firstEntry.getKey());
                if (!comparator.equal(firstEntry.getValue(), secondValue))
                    return false;
            }

            return true;
        }

        return first == null && second == null;
    }

    @Override
    public M objectsNotContainsInSecondMap(M first, M second) {
        M map = mapFactory.createNew();

        if (first != null && second != null) {
            for (Map.Entry<K, V> firstEntry : first.entrySet()) {
                V secondValue = second.get(firstEntry.getKey());
                if (secondValue == null) {
                    map.put(firstEntry.getKey(), firstEntry.getValue());
                }
            }
        }

        return map;
    }

    @Override
    public M objectsNotContainsInSecondMapButEqualsByKey(M first, M second) {
        M map = mapFactory.createNew();

        if (first != null && second != null) {
            for (Map.Entry<K, V> firstEntry : first.entrySet()) {
                V secondValue = second.get(firstEntry.getKey());
                if (secondValue != null && !comparator.equal(firstEntry.getValue(), secondValue)) {
                    map.put(firstEntry.getKey(), secondValue);
                }
            }
        }

        return map;
    }

}
