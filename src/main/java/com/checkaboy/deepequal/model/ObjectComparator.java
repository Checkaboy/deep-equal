package com.checkaboy.deepequal.model;

import com.checkaboy.deepequal.model.interf.IComparator;
import com.checkaboy.deepequal.model.interf.IObjectComparator;

import java.util.List;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectComparator<O>
        extends AbstractMapObjectComparator<O>
        implements IObjectComparator<O> {

    public ObjectComparator() {
    }

    public ObjectComparator(Map<String, IComparator<O>> fieldComparatorMap) {
        super(fieldComparatorMap);
    }

    @Override
    public boolean equal(O first, O second) {
        for (Map.Entry<String, IComparator<O>> eq : entrySet()) {
            if (!eq.getValue().equal(first, second))
                return false;
        }
        return true;
    }

    @Override
    public boolean fieldEqual(String fieldName, O first, O second) {
        return false;
    }

    @Override
    public List<String> valueFieldsEqual(boolean value, O first, O second) {
        return List.of();
    }

}
