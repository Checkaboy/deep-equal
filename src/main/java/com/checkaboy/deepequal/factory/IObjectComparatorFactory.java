package com.checkaboy.deepequal.factory;

import com.checkaboy.deepequal.model.ObjectComparator;
import com.checkaboy.deepequal.model.interf.IComparator;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectComparatorFactory<O> {

    ObjectComparator<O> createNew(Map<String, IComparator<O>> fieldComparatorMap);

}
