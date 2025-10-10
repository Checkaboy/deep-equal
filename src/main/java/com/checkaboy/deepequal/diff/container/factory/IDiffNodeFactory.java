package com.checkaboy.deepequal.diff.container.factory;

import com.checkaboy.deepequal.diff.container.IDiffNode;

/**
 * @author Taras Shaptala
 */
public interface IDiffNodeFactory {

    IDiffNode create(String fieldName, Object source, Object target);

}
