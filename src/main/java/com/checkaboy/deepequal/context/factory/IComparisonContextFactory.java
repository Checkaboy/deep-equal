package com.checkaboy.deepequal.context.factory;

import com.checkaboy.deepequal.context.cache.IComparisonContext;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface IComparisonContextFactory {

    IComparisonContext create();

}
