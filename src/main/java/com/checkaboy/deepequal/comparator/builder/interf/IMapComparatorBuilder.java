package com.checkaboy.deepequal.comparator.builder.interf;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.strategy.map.IMapComparisonStrategy;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapComparatorBuilder<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IComparatorBuilder<SM, TM> {

    IMapComparatorBuilder<SM, SK, SV, TM, TK, TV> setStrategy(IMapComparisonStrategy<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> strategy);

    IMapComparatorBuilder<SM, SK, SV, TM, TK, TV> setComparator(IFieldComparator<SV, TV> comparator);

}
