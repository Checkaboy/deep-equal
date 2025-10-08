package com.checkaboy.deepequal.diff.container;

/**
 * @author Taras Shaptala
 */
public class FieldDifference
        implements IFieldDifference {

    private final String path;
    private final Object sourceValue;
    private final Object targetValue;

    public FieldDifference(String path, Object sourceValue, Object targetValue) {
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
    public String toString() {
        return path + ": " + sourceValue + " != " + targetValue;
    }

}
