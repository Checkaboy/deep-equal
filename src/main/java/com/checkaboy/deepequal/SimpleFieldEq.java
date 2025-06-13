package com.checkaboy.deepequal;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class SimpleFieldEq<O, V>
        extends FieldEq<O, V> {

    public SimpleFieldEq(Function<O, V> extractor) {
        super(extractor, Objects::equals);
    }

}
