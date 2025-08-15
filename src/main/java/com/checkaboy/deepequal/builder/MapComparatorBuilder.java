package com.checkaboy.deepequal.builder;

import com.checkaboy.deepequal.builder.interf.IMapComparatorBuilder;
import com.checkaboy.deepequal.comparator.MapComparator;
import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.interf.IMapComparator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class MapComparatorBuilder<K, V>
        implements IMapComparatorBuilder<Map<K, V>, K, V> {

    private final Class<K> keyType;
    private final Class<V> valueType;
    private Supplier<Map<K, V>> constructor = HashMap::new;
    private IFieldComparator<V> comparator = Objects::equals;

    public MapComparatorBuilder(Class<K> keyType, Class<V> type) {
        this.keyType = keyType;
        this.valueType = type;
    }

    @Override
    public MapComparatorBuilder<K, V> setConstructor(Supplier<Map<K, V>> constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public MapComparatorBuilder<K, V> setComparator(IFieldComparator<V> comparator) {
        this.comparator = comparator;
        return this;
    }

    public IMapComparator<Map<K, V>, K, V> build() {
        return new MapComparator<>(constructor, comparator);
    }

    public Class<K> getKeyType() {
        return keyType;
    }

    public Class<V> getValueType() {
        return valueType;
    }

}
