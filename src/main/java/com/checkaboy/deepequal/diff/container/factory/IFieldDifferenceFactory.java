package com.checkaboy.deepequal.diff.container.factory;

import com.checkaboy.deepequal.diff.container.IFieldDifference;

/**
 * @author Taras Shaptala
 */
public interface IFieldDifferenceFactory {

    IFieldDifference create(String fieldName, Object source, Object target);

}
