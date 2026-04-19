package com.checkaboy.deepequal.dsl;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public final class DeepEqualDsl {

    private DeepEqualDsl() {
    }

    public static <S, V> FieldRule<S, V, S, V> field(String name, Function<S, V> extractor) {
        return new FieldRule<>(name, extractor, extractor);
    }

    public static <SO, SV, TO, TV> FieldRule<SO, SV, TO, TV> field(
            String name,
            Function<SO, SV> sourceExtractor,
            Function<TO, TV> targetExtractor
    ) {
        return new FieldRule<>(name, sourceExtractor, targetExtractor);
    }

}
