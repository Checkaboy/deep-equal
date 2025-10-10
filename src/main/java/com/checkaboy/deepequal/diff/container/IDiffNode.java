package com.checkaboy.deepequal.diff.container;

import java.util.List;

/**
 * @author Taras Shaptala
 */
public interface IDiffNode {

    String getPath();

    Object getSourceValue();

    Object getTargetValue();

    List<IDiffNode> getChildren();

    void addChild(IDiffNode child);

}
