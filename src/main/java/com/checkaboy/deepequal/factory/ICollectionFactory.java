package com.checkaboy.deepequal.factory;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionFactory<C extends Collection<O>, O> {

    C createNew();

}
