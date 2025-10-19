package com.checkaboy.deepequal.diff.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class DiffNode
        implements IDiffNode {

    private final String path;
    private final Object sourceValue;
    private final Object targetValue;
    private final List<IDiffNode> children = new ArrayList<>();

    public DiffNode(String path, Object sourceValue, Object targetValue) {
        this.path = path;
        this.sourceValue = sourceValue;
        this.targetValue = targetValue;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getSourceValue() {
        return sourceValue;
    }

    @Override
    public Object getTargetValue() {
        return targetValue;
    }

    @Override
    public List<IDiffNode> getChildren() {
        return children;
    }

    @Override
    public void addChild(IDiffNode child) {
        children.add(child);
    }

    @Override
    public void addChildren(Collection<IDiffNode> children) {
        this.children.addAll(children);
    }

    @Override
    public String toString() {
        return "DiffNode{" +
                "path='" + path + '\'' +
                ", sourceValue=" + sourceValue +
                ", targetValue=" + targetValue +
                ", children=" + children +
                '}';
    }

}
