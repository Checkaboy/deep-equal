package com.checkaboy.deepequal.comparator.model;

import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.model.interf.IIdentifiedCollectionComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class IdentifiedCollectionComparator<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends CollectionComparator<SC, SV, TC, TV>
        implements IIdentifiedCollectionComparator<SC, SV, TC, TV> {

    private final IFieldComparator<SV, TV> identifierComparator;

    public IdentifiedCollectionComparator(/*Supplier<C> constructor, */IFieldComparator<SV, TV> comparator, IFieldComparator<SV, TV> identifierComparator) {
        super(/*constructor, */comparator);
        this.identifierComparator = identifierComparator;
    }

    @Override
    public boolean equal(SC source, TC target) {
        if (source != null && target != null) {
            if (source.size() != target.size())
                return false;

            for (SV sourceValue : source) {
                TV find = findObjectsByIdentificationComparator(sourceValue, target);
                if (!comparator.equal(sourceValue, find))
                    return false;
            }

            return true;
        }

        return source == null && target == null;
    }

    protected TV findObjectsByIdentificationComparator(SV object, Collection<TV> collection) {
        List<TV> find = new ArrayList<>();

        for (TV secondValue : collection) {
            if (identifierComparator.equal(object, secondValue))
                find.add(secondValue);
        }

        if (find.size() == 1) return find.get(0);
        else throw new IllegalArgumentException("Identifier comparator found more than 1 object");
    }

//    @Override
//    public C objectsNotContainsInSecondCollectionButEqualsByIdentifier(C first, C second) {
//        C collection = constructor.get();
//
//        if (first != null && second != null) {
//            for (V firstValue : first) {
//                V find = findObjectsByIdentificationComparator(firstValue, second);
//                if (!comparator.equal(firstValue, find)) {
//                    collection.add(find);
//                }
//            }
//        }
//
//        return collection;
//    }

}
