package com.checkaboy.deepequal.comparator.builder;

import com.checkaboy.deepequal.comparator.builder.interf.IFieldComparatorBuilder;
import com.checkaboy.deepequal.comparator.model.FieldComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class FieldComparatorBuilder<SO, SV, TO, TV>
        extends AbstractTypifiedContainer<TO>
        implements IFieldComparatorBuilder<SO, SV, TO, TV> {

    private Function<SO, SV> sourceExtractor = o -> null;
    private Function<TO, TV> targetExtractor = o -> null;
    private IFieldComparator<SV, TV> comparator = Objects::equals;

    public FieldComparatorBuilder(Class<SO> sourceClass, Class<TO> targetClass) {
        super(targetClass);
    }

    @Override
    public FieldComparatorBuilder<SO, SV, TO, TV> setSourceExtractor(Function<SO, SV> extractor) {
        this.sourceExtractor = extractor;
        return this;
    }

    @Override
    public FieldComparatorBuilder<SO, SV, TO, TV> setTargetExtractor(Function<TO, TV> extractor) {
        this.targetExtractor = extractor;
        return this;
    }

    @Override
    public FieldComparatorBuilder<SO, SV, TO, TV> setComparator(IFieldComparator<SV, TV> comparator) {
        this.comparator = comparator;
        return this;
    }

    public IFieldComparator<SO, TO> build() {
        return new FieldComparator<>(sourceExtractor, targetExtractor, comparator);
    }

}
