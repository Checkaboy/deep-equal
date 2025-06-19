package com.checkaboy.deepequal.factory;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapFactory<M extends Map<K, V>, K, V> {

    M createNew();

}
