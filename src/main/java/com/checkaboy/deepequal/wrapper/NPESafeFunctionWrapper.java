package com.checkaboy.deepequal.wrapper;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
//TODO Move to util module
public class NPESafeFunctionWrapper<O, V>
        implements Function<O, V> {

    private final Function<O, V> extractor;

    public NPESafeFunctionWrapper(Function<O, V> extractor) {
        this.extractor = extractor;
    }

    @Override
    public V apply(O o) {
        if (o == null) return null;
        return extractor.apply(o);
    }

}
