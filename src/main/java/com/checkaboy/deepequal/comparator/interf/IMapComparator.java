package com.checkaboy.deepequal.comparator.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapComparator<M extends Map<K, V>, K, V>
        extends IFieldComparator<M> {

    M objectsNotContainsInSecondMap(M first, M second);

    M objectsNotContainsInSecondMapButEqualsByKey(M first, M second);

}
