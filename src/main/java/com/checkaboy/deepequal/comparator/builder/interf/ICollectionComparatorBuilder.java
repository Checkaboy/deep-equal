package com.checkaboy.deepequal.comparator.builder.interf;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.strategy.collection.ICollectionComparisonStrategy;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionComparatorBuilder<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV> {

    ICollectionComparatorBuilder<SC, SV, TC, TV> setStrategy(ICollectionComparisonStrategy<SC, SV, TC, TV> strategy);

    ICollectionComparatorBuilder<SC, SV, TC, TV> setComparator(IFieldComparator<SV, TV> comparator);

}
