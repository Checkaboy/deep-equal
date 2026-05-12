package com.checkaboy.deepequal.context.cache;

import com.checkaboy.deepequal.comparator.v2.ComparatorRegistry;
import com.checkaboy.deepequal.comparator.v2.ComparisonPath;
import com.checkaboy.deepequal.comparator.v2.ComparisonPolicy;

/**
 * @author Taras Shaptala
 */
public interface IComparisonContext {

    boolean enter(Object a, Object b);

    ComparatorRegistry registry();

    ComparisonPolicy policy();

    ComparisonPath path();

}
