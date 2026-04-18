package com.checkaboy.deepequal.comparator.map;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapComparator<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IFieldComparator<SM, TM> {
}
