package com.checkaboy.deepequal.dsl;

import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;

import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class NamedFieldDiffCollector<S, T> {

    private final String name;
    private final IFieldDiffCollector<S, T> diffCollector;

    public NamedFieldDiffCollector(String name, IFieldDiffCollector<S, T> diffCollector) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.diffCollector = Objects.requireNonNull(diffCollector, "diffCollector must not be null");
    }

    public String getName() {
        return name;
    }

    public IFieldDiffCollector<S, T> getDiffCollector() {
        return diffCollector;
    }

}
