package com.checkaboy.deepequal.diff.model;

import com.checkaboy.deepequal.comparator.model.FieldComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.context.cache.IComparisonContext;
import com.checkaboy.deepequal.diff.container.IFieldDifference;
import com.checkaboy.deepequal.diff.container.factory.IFieldDifferenceFactory;
import com.checkaboy.deepequal.diff.model.interf.IDiffCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectDiffCollector<S, T>
        extends HashMap<String, IFieldComparator<S, T>>
        implements IDiffCollector<S, T> {

    private final String rootName;
    private final IFieldDifferenceFactory fieldDifferenceFactory;

    public ObjectDiffCollector(IFieldDifferenceFactory fieldDifferenceFactory) {
        this("<root>", fieldDifferenceFactory);
    }

    public ObjectDiffCollector(String rootName, IFieldDifferenceFactory fieldDifferenceFactory) {
        this.rootName = rootName;
        this.fieldDifferenceFactory = fieldDifferenceFactory;
    }

    @Override
    public List<IFieldDifference> collectDifferences(IComparisonContext comparisonContext, S source, T target) {
        List<IFieldDifference> differences = new ArrayList<>();

        if (source == null && target == null) return differences;
        if (source == null || target == null) {
            differences.add(fieldDifferenceFactory.create(rootName, source, target));
            return differences;
        }

        for (Map.Entry<String, IFieldComparator<S, T>> entry : entrySet()) {
            String fieldName = entry.getKey();
            IFieldComparator<S, T> comparator = entry.getValue();

            boolean equal = comparator.compare(comparisonContext, source, target);
            if (!equal) {
//                Object sourceValue = ((FieldComparator<S, T>) comparator).extractSource(source);
//                Object targetValue = ((FieldComparator<S, T>) comparator).extractTarget(target);
//                differences.add(new FieldDifference(fieldName, sourceValue, targetValue));
            }
        }

        return differences;
    }

}
