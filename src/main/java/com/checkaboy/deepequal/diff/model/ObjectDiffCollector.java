package com.checkaboy.deepequal.diff.model;

import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IDiffNode;
import com.checkaboy.deepequal.diff.container.factory.IDiffNodeFactory;
import com.checkaboy.deepequal.diff.model.interf.IDiffCollector;
import com.checkaboy.deepequal.diff.model.interf.IFieldDiffCollector;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectDiffCollector<S, T>
        extends HashMap<String, IFieldDiffCollector<S, T>>
        implements IDiffCollector<S, T> {

    private final String fieldName;

    public ObjectDiffCollector(int initialCapacity, float loadFactor, String fieldName) {
        super(initialCapacity, loadFactor);
        this.fieldName = fieldName;
    }

    public ObjectDiffCollector(int initialCapacity, String fieldName) {
        super(initialCapacity);
        this.fieldName = fieldName;
    }

    public ObjectDiffCollector(String fieldName) {
        this.fieldName = fieldName;
    }

    public ObjectDiffCollector(Map<? extends String, ? extends IFieldDiffCollector<S, T>> m, String fieldName) {
        super(m);
        this.fieldName = fieldName;
    }

    @Override
    public IDiffNode collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, S source, T target, String currentPath) {
        if (source == null && target == null) return null;
        final String fullPath = currentPath + "." + fieldName;
        IDiffNode node = diffNodeFactory.create(fullPath, source, target);

        if (source == null || target == null) return node;

        for (Map.Entry<String, IFieldDiffCollector<S, T>> entry : entrySet()) {
            IDiffNode childNode = entry.getValue().collect(comparisonContext, diffNodeFactory, source, target, fullPath);
            if (childNode != null) node.addChild(childNode);
        }

        return !node.getChildren().isEmpty() ? node : null;
    }

}
