package com.checkaboy.deepequal.dsl;

import com.checkaboy.deepequal.comparator.field.IFieldComparator;

import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class NamedFieldComparator<S, T> {

    private final String name;
    private final IFieldComparator<S, T> comparator;

    public NamedFieldComparator(String name, IFieldComparator<S, T> comparator) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.comparator = Objects.requireNonNull(comparator, "comparator must not be null");
    }

    public String getName() {
        return name;
    }

    public IFieldComparator<S, T> getComparator() {
        return comparator;
    }

}
