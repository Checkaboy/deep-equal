package com.checkaboy.deepequal.comparator.field.builder;

import com.checkaboy.deepequal.IBuilder;
import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IFieldComparatorBuilder<SO, SV, TO, TV>
        extends IBuilder<IComparator<SO, TO>> {

    IFieldComparatorBuilder<SO, SV, TO, TV> setSourceExtractor(Function<SO, SV> extractor);

    IFieldComparatorBuilder<SO, SV, TO, TV> setTargetExtractor(Function<TO, TV> extractor);

    IFieldComparatorBuilder<SO, SV, TO, TV> setComparator(IFieldComparator<SV, TV> comparator);

}
