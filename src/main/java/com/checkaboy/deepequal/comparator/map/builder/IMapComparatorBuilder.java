package com.checkaboy.deepequal.comparator.map.builder;

import com.checkaboy.deepequal.comparator.IComparatorBuilder;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.comparator.map.strategy.IMapComparisonStrategy;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapComparatorBuilder<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IComparatorBuilder<SM, TM> {

    IMapComparatorBuilder<SM, SK, SV, TM, TK, TV> setStrategy(IMapComparisonStrategy<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> strategy);

    IMapComparatorBuilder<SM, SK, SV, TM, TK, TV> setComparator(IFieldComparator<SV, TV> comparator);

}
