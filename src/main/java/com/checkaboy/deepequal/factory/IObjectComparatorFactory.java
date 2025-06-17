package com.checkaboy.deepequal.factory;

import com.checkaboy.deepequal.model.single.ObjectComparator;
import com.checkaboy.deepequal.model.single.interf.IComparator;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectComparatorFactory<O> {

    ObjectComparator<O> createNew(Map<String, IComparator<O>> fieldComparatorMap);

}
