package com.checkaboy.deepequal.comparator.v2;

import com.checkaboy.deepequal.context.cache.IComparisonContext;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class ComparisonContext
        implements IComparisonContext {

    private final Set<ComparisonPair> visited = new HashSet<>();
    private final Deque<String> path = new ArrayDeque<>();
    private final ComparatorRegistry registry;
    private final ComparisonProfile profile;

    public ComparisonContext(ComparatorRegistry registry, ComparisonProfile profile) {
        this.registry = registry;
        this.profile = profile;
    }

    @Override
    public boolean enter(Object a, Object b) {
        return visited.add(new ComparisonPair(a, b));
    }

    public ComparisonProfile profile() {
        return profile;
    }

    public ComparatorRegistry registry() {
        return registry;
    }

    @Override
    public ComparisonPolicy policy() {
        return null;
    }

    @Override
    public ComparisonPath path() {
        return null;
    }

    public void pushPath(String node) {
        path.push(node);
    }

    public void popPath() {
        if (!path.isEmpty()) {
            path.pop();
        }
    }

    public String currentPath() {
        return String.join(".", path);
    }

    private static final class ComparisonPair {
        private final Object left;
        private final Object right;

        private ComparisonPair(Object left, Object right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ComparisonPair)) return false;
            ComparisonPair that = (ComparisonPair) o;
            return left == that.left && right == that.right;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(left) * 31 + System.identityHashCode(right);
        }
    }

}
