package com.checkaboy.deepequal.comparator.builder.interf;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionComparatorBuilder<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV> {

//    ICollectionComparatorBuilder<C, V> setConstructor(Supplier<C> constructor);

    ICollectionComparatorBuilder<SC, SV, TC, TV> setComparator(IFieldComparator<SV, TV> comparator);

    ICollectionComparatorBuilder<SC, SV, TC, TV> setIdentifierComparator(IFieldComparator<SV, TV> identifierComparator);
}
