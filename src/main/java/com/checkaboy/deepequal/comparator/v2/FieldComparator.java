package com.checkaboy.deepequal.comparator.v2;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.google.common.reflect.TypeToken;

import java.util.Objects;
import java.util.function.Function;

public class FieldComparator<SO, SV, TO, TV>
        implements IComparator<SO, TO> {

    private final TypeToken<SV> sourceValueType;
    private final TypeToken<TV> targetValueType;
    private final Function<SO, SV> sourceExtractor;
    private final Function<TO, TV> targetExtractor;
    private final IComparator<SV, TV> explicitComparator;

    public FieldComparator(
            TypeToken<SV> sourceValueType,
            TypeToken<TV> targetValueType,
            Function<SO, SV> sourceExtractor,
            Function<TO, TV> targetExtractor,
            IComparator<SV, TV> explicitComparator
    ) {
        this.sourceValueType = sourceValueType;
        this.targetValueType = targetValueType;
        this.sourceExtractor = sourceExtractor;
        this.targetExtractor = targetExtractor;
        this.explicitComparator = explicitComparator;
    }

    @Override
    public boolean compare(IComparisonContext ctx, SO source, TO target) {
        SV sourceValue = sourceExtractor.apply(source);
        TV targetValue = targetExtractor.apply(target);
        if (explicitComparator != null) {
            return explicitComparator.compare(ctx, sourceValue, targetValue);
        }
        ComparisonContext context = (ComparisonContext) ctx;
        IComparator<SV, TV> comparator = context.registry().resolve(sourceValueType, targetValueType, context);
        return comparator.compare(ctx, sourceValue, targetValue);
    }

    public static <SO, SV, TO, TV> FieldComparator<SO, SV, TO, TV> of(
            TypeToken<SV> sv, TypeToken<TV> tv,
            Function<SO, SV> se, Function<TO, TV> te
    ) {
        return new FieldComparator<>(sv, tv, se, te, (c, s, t) -> Objects.equals(s, t));
    }

}
