package com.checkaboy.deepequal.context.cache;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public class ComparisonContext
        implements IComparisonContext {

    private final Set<ComparisonPair> visited;

    public ComparisonContext() {
        this(new HashSet<>());
    }

    public ComparisonContext(Set<ComparisonPair> visited) {
        this.visited = visited;
    }

    @Override
    public boolean enter(Object a, Object b) {
        return visited.add(new ComparisonPair(a, b));
    }

    public static class ComparisonPair {
        private final Object a;
        private final Object b;

        ComparisonPair(Object a, Object b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ComparisonPair)) return false;
            ComparisonPair that = (ComparisonPair) o;
            return a == that.a && b == that.b;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(a) * 31 + System.identityHashCode(b);
        }
    }

}
