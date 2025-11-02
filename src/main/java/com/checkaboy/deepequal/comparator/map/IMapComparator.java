package com.checkaboy.deepequal.comparator.map;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapComparator<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IFieldComparator<SM, TM> {

    //TODO Move to utils module
//    M objectsNotContainsInSecondMap(M first, M second);

    //TODO Move to utils module
//    M objectsNotContainsInSecondMapButEqualsByKey(M first, M second);

}
