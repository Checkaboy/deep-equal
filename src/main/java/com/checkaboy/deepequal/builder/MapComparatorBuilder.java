package com.checkaboy.deepequal.builder;

import com.checkaboy.deepequal.builder.interf.IMapComparatorBuilder;
import com.checkaboy.deepequal.comparator.MapComparator;
import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.interf.IMapComparator;
import com.checkaboy.deepequal.factory.IMapFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class MapComparatorBuilder<K, V>
        implements IMapComparatorBuilder<Map<K, V>, K, V> {

    private final Class<K> keyType;
    private final Class<V> valueType;
    private IMapFactory<Map<K, V>, K, V> mapFactory = HashMap::new;
    private IFieldComparator<V> comparator = Objects::equals;

    public MapComparatorBuilder(Class<K> keyType, Class<V> type) {
        this.keyType = keyType;
        this.valueType = type;
    }

    @Override
    public void setMapFactory(IMapFactory<Map<K, V>, K, V> mapFactory) {
        this.mapFactory = mapFactory;
    }

    @Override
    public void setComparator(IFieldComparator<V> comparator) {
        this.comparator = comparator;
    }

    public IMapComparator<Map<K, V>, K, V> build() {
        return new MapComparator<>(mapFactory, comparator);
    }

    public Class<K> getKeyType() {
        return keyType;
    }

    public Class<V> getValueType() {
        return valueType;
    }

}
