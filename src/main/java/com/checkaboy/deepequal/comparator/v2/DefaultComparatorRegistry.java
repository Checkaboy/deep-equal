package com.checkaboy.deepequal.comparator.v2;

import com.checkaboy.deepequal.comparator.IComparator;
import com.google.common.reflect.TypeToken;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DefaultComparatorRegistry
        implements ComparatorRegistry {

    private final Map<Key, ComparatorFactory<?, ?>> factories = new LinkedHashMap<>();

    @Override
    public synchronized <S, T> void register(TypeToken<S> source, TypeToken<T> target, ComparatorFactory<S, T> factory) {
        factories.put(new Key(source, target), Objects.requireNonNull(factory, "factory must not be null"));
    }

    @Override
    public synchronized <S, T> IComparator<S, T> resolve(TypeToken<S> source, TypeToken<T> target, ComparisonContext context) {
        ComparatorFactory<S, T> factory = castFactory(factories.get(new Key(source, target)));
        if (factory == null) {
            throw new IllegalStateException("No comparator registered for " + source + " -> " + target);
        }
        return factory.create(context);
    }

    @SuppressWarnings("unchecked")
    private static <S, T> ComparatorFactory<S, T> castFactory(ComparatorFactory<?, ?> factory) {
        return (ComparatorFactory<S, T>) factory;
    }

    private static final class Key {
        private final TypeToken<?> source;
        private final TypeToken<?> target;

        private Key(TypeToken<?> source, TypeToken<?> target) {
            this.source = source;
            this.target = target;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key key = (Key) o;
            return source.equals(key.source) && target.equals(key.target);
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, target);
        }
    }

}
