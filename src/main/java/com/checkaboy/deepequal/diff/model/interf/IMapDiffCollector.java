package com.checkaboy.deepequal.diff.model.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IMapDiffCollector<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IDiffCollector<SM, TM> {
}
