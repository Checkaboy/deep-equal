package com.checkaboy.deepequal.comparator.v2;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.context.cache.ComparisonContext;

public interface ComparatorFactory<S, T> {

    IComparator<S, T> create(ComparisonContext context);

}
