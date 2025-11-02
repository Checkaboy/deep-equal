package com.checkaboy.deepequal.comparator.map.strategy;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class IdentifiedMapComparisonStrategy<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        implements IMapComparisonStrategy<SM, SK, SV, TM, TK, TV> {

    private final Function<SK, TK> keyCaster;

    public IdentifiedMapComparisonStrategy(Function<SK, TK> keyCaster) {
        this.keyCaster = keyCaster;
    }

    @Override
    public boolean compare(IComparisonContext comparisonContext, SM source, TM target, IFieldComparator<SV, TV> comparator) {
        for (Map.Entry<SK, SV> entry : source.entrySet()) {
            TV targetValue = target.get(keyCaster.apply(entry.getKey()));
            if (!comparator.compare(comparisonContext, entry.getValue(), targetValue))
                return false;
        }

        return true;
    }

}
