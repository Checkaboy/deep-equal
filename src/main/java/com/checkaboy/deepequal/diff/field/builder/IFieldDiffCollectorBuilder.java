package com.checkaboy.deepequal.diff.field.builder;

import com.checkaboy.deepequal.IBuilder;
import com.checkaboy.deepequal.diff.IDiffCollector;
import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IFieldDiffCollectorBuilder<SO, SV, TO, TV>
        extends IBuilder<IDiffCollector<SO, TO>> {

    IFieldDiffCollectorBuilder<SO, SV, TO, TV> setSourceExtractor(Function<SO, SV> extractor);

    IFieldDiffCollectorBuilder<SO, SV, TO, TV> setTargetExtractor(Function<TO, TV> extractor);

    IFieldDiffCollectorBuilder<SO, SV, TO, TV> setDiffCollector(IFieldDiffCollector<SV, TV> diffCollector);

}
