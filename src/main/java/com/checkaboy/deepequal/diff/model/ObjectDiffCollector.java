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

    public ObjectDiffCollector(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ObjectDiffCollector(int initialCapacity) {
        super(initialCapacity);
    }

    public ObjectDiffCollector(String fieldName) {
    }

    public ObjectDiffCollector(Map<? extends String, ? extends IFieldDiffCollector<S, T>> m) {
        super(m);
    }

    @Override
    public IDiffNode collect(IComparisonContext comparisonContext, IDiffNodeFactory diffNodeFactory, S source, T target, String currentPath) {
        if (source == null && target == null) return null;
        IDiffNode node = diffNodeFactory.create(currentPath, source, target);

        if (source == null || target == null) return node;

        for (Map.Entry<String, IFieldDiffCollector<S, T>> entry : entrySet()) {
            final String fullPath = currentPath + "." + entry.getKey();
            IDiffNode childNode = entry.getValue().collect(comparisonContext, diffNodeFactory, source, target, fullPath);
            if (childNode != null) node.addChild(childNode);
        }

        return !node.getChildren().isEmpty() ? node : null;
    }

}
