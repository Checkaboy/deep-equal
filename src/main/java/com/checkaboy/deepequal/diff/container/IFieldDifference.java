package com.checkaboy.deepequal.diff.container;

/**
 * @author Taras Shaptala
 */
public interface IFieldDifference {

    String getPath();

    Object getSourceValue();

    Object getTargetValue();

}
