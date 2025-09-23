package com.checkaboy.deepequal.comparator.model;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.model.interf.IMapComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class MapComparator<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        implements IMapComparator<SM, SK, SV, TM, TK, TV> {

    protected final Function<SK, TK> keyCaster;
    protected final IFieldComparator<SV, TV> comparator;

    public MapComparator(Function<SK, TK> keyCaster, IFieldComparator<SV, TV> comparator) {
        this.keyCaster = keyCaster;
        this.comparator = comparator;
    }

    @Override
    public boolean compare(IComparisonContext comparisonContext, SM source, TM target) {
        if (source != null && target != null) {
            if (source.size() != target.size())
                return false;

            for (Map.Entry<SK, SV> firstEntry : source.entrySet()) {
                // TODO transform key SK -> TK ??? OR K is equal type in generic template
                //  cast can destruct hash codes contract. But identifier objects (DTO/Entity)
                //  can save hash codes contract...
                TV secondValue = target.get(keyCaster.apply(firstEntry.getKey()));
                if (!comparator.compare(comparisonContext, firstEntry.getValue(), secondValue))
                    return false;
            }

            return true;
        }

        return source == null && target == null;
    }

//    @Override
//    public M objectsNotContainsInSecondMap(M first, M second) {
//        M map = constructor.get();
//
//        if (first != null && second != null) {
//            for (Map.Entry<K, V> firstEntry : first.entrySet()) {
//                V secondValue = second.get(firstEntry.getKey());
//                if (secondValue == null) {
//                    map.put(firstEntry.getKey(), firstEntry.getValue());
//                }
//            }
//        }
//
//        return map;
//    }

//    @Override
//    public M objectsNotContainsInSecondMapButEqualsByKey(M first, M second) {
//        M map = constructor.get();
//
//        if (first != null && second != null) {
//            for (Map.Entry<K, V> firstEntry : first.entrySet()) {
//                V secondValue = second.get(firstEntry.getKey());
//                if (secondValue != null && !comparator.equal(firstEntry.getValue(), secondValue)) {
//                    map.put(firstEntry.getKey(), secondValue);
//                }
//            }
//        }
//
//        return map;
//    }

}
