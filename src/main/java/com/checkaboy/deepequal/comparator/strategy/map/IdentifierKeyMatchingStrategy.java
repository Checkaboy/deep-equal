package com.checkaboy.deepequal.comparator.strategy.map;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class IdentifierKeyMatchingStrategy<SK, SV, TK, TV>
        implements IKeyMatchingStrategy<SK, SV, TK, TV> {

    private final IFieldComparator<SK, TK> idComparator;

    public IdentifierKeyMatchingStrategy(IFieldComparator<SK, TK> idComparator) {
        this.idComparator = idComparator;
    }

    @Override
    public TV findMatchingValue(IComparisonContext ctx, SK sourceKey, SV sourceValue, Map<TK, TV> target) {
        for (Map.Entry<TK, TV> entry : target.entrySet()) {
            if (idComparator.compare(ctx, sourceKey, entry.getKey()))
                return entry.getValue();
        }
        return null;
    }

}
