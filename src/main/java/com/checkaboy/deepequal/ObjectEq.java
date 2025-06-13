package com.checkaboy.deepequal;

import java.util.List;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectEq<O>
        extends AbstractObjectEq<O>
        implements IFieldEq<O>, IObjectEq<O> {

    @Override
    public boolean equal(O first, O second) {
        for (Map.Entry<String, IFieldEq<O>> eq : entrySet()) {
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
    public List<String> fields(boolean value, O first, O second) {
        return List.of();
    }

}
