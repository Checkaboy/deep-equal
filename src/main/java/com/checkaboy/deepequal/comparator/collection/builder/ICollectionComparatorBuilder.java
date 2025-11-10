package com.checkaboy.deepequal.comparator.collection.builder;

import com.checkaboy.deepequal.IBuilder;
import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.collection.strategy.ICollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionComparatorBuilder<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IBuilder<IComparator<SC, TC>> {

    ICollectionComparatorBuilder<SC, SV, TC, TV> setStrategy(ICollectionComparisonStrategy<SC, SV, TC, TV> strategy);

    ICollectionComparatorBuilder<SC, SV, TC, TV> setComparator(IFieldComparator<SV, TV> comparator);

}
