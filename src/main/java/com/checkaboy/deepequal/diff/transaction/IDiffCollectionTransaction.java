package com.checkaboy.deepequal.diff.transaction;

import com.checkaboy.deepequal.diff.container.IDiffNode;

/**
 * @author Taras Shaptala
 */
public interface IDiffCollectionTransaction<S, T> {

    IDiffNode collect(S source, T target, String currentPath);

}
