package com.checkaboy.deepequal.comparator.builder.interf;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapComparatorBuilder<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV> {

//    IMapComparatorBuilder<M, K, V> setConstructor(Supplier<Map<K, V>> constructor);

    IMapComparatorBuilder<SM, SK, SV, TM, TK, TV> setComparator(IFieldComparator<SV, TV> comparator);

}
