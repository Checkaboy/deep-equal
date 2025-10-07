package com.checkaboy.deepequal.diff.model;

/**
 * @author Taras Shaptala
 */
public interface IFieldDifference {

    String getPath();

    Object getSourceValue();

    Object getTargetValue();

}
