package com.checkaboy.deepequal;

import java.util.List;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class SubClassEq<O, S>
        implements IFieldEq<O>, IObjectEq<S> {

    private final Function<O, S> extractor;
    private final ObjectEq<S> objectEq;

    public SubClassEq(Function<O, S> extractor, ObjectEq<S> objectEq) {
        this.extractor = extractor;
        this.objectEq = objectEq;
    }

    @Override
    public boolean equal(O first, O second) {
        return objectEq.equal(extractor.apply(first), extractor.apply(second));
    }

    public IFieldEq<S> putFieldEq(String fieldName, IFieldEq<S> eq) {
        return objectEq.put(fieldName, eq);
    }

    @Override
    public boolean fieldEqual(String fieldName, S first, S second) {
        return false;
    }

    @Override
    public List<String> fields(boolean value, S first, S second) {
        return List.of();
    }

}
