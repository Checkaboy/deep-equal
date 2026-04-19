package com.checkaboy.deepequal.comparator.object.builder;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.field.IFieldComparator;
import com.checkaboy.deepequal.dsl.NamedFieldComparator;
import com.checkaboy.objectutils.model.IBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IObjectComparatorBuilder<S, T>
        extends IBuilder<IComparator<S, T>> {

    IObjectComparatorBuilder<S, T> set(Map<String, ? extends IFieldComparator<S, T>> fieldComparatorMap);

    IObjectComparatorBuilder<S, T> put(String fieldName, IFieldComparator<S, T> fieldComparator);

    IObjectComparatorBuilder<S, T> put(NamedFieldComparator<S, T> namedFieldComparator);

    <SV, TV> IObjectComparatorBuilder<S, T> put(
            String fieldName,
            Function<S, SV> sourceExtractor,
            Function<T, TV> targetExtractor,
            IComparator<SV, TV> comparator
    );

    <V> IObjectComparatorBuilder<S, T> put(
            String fieldName,
            Function<S, V> sourceExtractor,
            Function<T, V> targetExtractor
    );

    IObjectComparatorBuilder<S, T> putAll(Map<String, ? extends IFieldComparator<S, T>> fieldComparatorMap);

    IObjectComparatorBuilder<S, T> putAllBindings(Collection<? extends NamedFieldComparator<S, T>> namedFieldComparators);

}
