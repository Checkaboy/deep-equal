package com.checkaboy.deepequal.diff.map;

import com.checkaboy.deepequal.diff.field.IFieldDiffCollector;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapDiffCollector<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IFieldDiffCollector<SM, TM> {
}
