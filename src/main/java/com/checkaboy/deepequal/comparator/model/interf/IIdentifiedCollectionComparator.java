package com.checkaboy.deepequal.comparator.model.interf;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface IIdentifiedCollectionComparator<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends ICollectionComparator<SC, SV, TC, TV> {

    //TODO Move to utils module
//    C objectsNotContainsInSecondCollectionButEqualsByIdentifier(C first, C second);

}
