package com.checkaboy.deepequal.comparator.builder;

import com.checkaboy.deepequal.comparator.builder.interf.IMapComparatorBuilder;
import com.checkaboy.deepequal.comparator.model.MapComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.model.interf.IMapComparator;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class MapComparatorBuilder<SK, SV, TK, TV>
        extends AbstractTypifiedContainer<TV>
        implements IMapComparatorBuilder<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> {

    //    private Supplier<Map<K, V>> constructor = HashMap::new;
    protected Function<SK, TK> keyCaster;
    protected IFieldComparator<SV, TV> comparator;

    public MapComparatorBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(targetType);
    }

//    @Override
//    public MapComparatorBuilder<K, V> setConstructor(Supplier<Map<K, V>> constructor) {
//        this.constructor = constructor;
//        return this;
//    }

    @Override
    public MapComparatorBuilder<SK, SV, TK, TV> setComparator(IFieldComparator<SV, TV> comparator) {
        this.comparator = comparator;
        return this;
    }

    public IMapComparator<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> build() {
        return new MapComparator<>(/*constructor,*/keyCaster, comparator);
    }

}
