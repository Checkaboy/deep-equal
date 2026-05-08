package com.checkaboy.deepequal.comparator.v2;

import com.checkaboy.deepequal.comparator.IComparator;
import com.google.common.reflect.TypeToken;

public interface ComparatorRegistry {

    <S, T> void register(TypeToken<S> source, TypeToken<T> target, ComparatorFactory<S, T> factory);

    <S, T> IComparator<S, T> resolve(TypeToken<S> source, TypeToken<T> target, ComparisonContext context);

}
