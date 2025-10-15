package com.checkaboy.deepequal.comparator.builder.interf;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IFieldComparatorBuilder<SO, SV, TO, TV>
        extends IComparatorBuilder<SO, TO> {

    IFieldComparatorBuilder<SO, SV, TO, TV> setSourceExtractor(Function<SO, SV> extractor);

    IFieldComparatorBuilder<SO, SV, TO, TV> setTargetExtractor(Function<TO, TV> extractor);

    IFieldComparatorBuilder<SO, SV, TO, TV> setComparator(IFieldComparator<SV, TV> comparator);

}
