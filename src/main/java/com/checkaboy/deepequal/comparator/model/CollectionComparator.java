package com.checkaboy.deepequal.comparator.model;

import com.checkaboy.deepequal.comparator.model.interf.ICollectionComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class CollectionComparator<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionComparator<SC, SV, TC, TV> {

//    protected final Supplier<C> constructor;
    protected final IFieldComparator<SV, TV> comparator;

    public CollectionComparator(/*Supplier<C> constructor, */IFieldComparator<SV, TV> comparator) {
//        this.constructor = constructor;
        this.comparator = comparator;
    }

    @Override
    public boolean equal(SC source, TC target) {
        if (source != null && target != null) {
            if (source.size() != target.size())
                return false;

            List<TV> secondDump = new ArrayList<>(target);

            for (SV firstValue : source) {
                boolean matched = false;

                Iterator<TV> iterator = secondDump.iterator();
                while (iterator.hasNext()) {
                    TV secondValue = iterator.next();
                    if (comparator.equal(firstValue, secondValue)) {
                        iterator.remove();
                        matched = true;
                        break;
                    }
                }
                if (!matched) return false;
            }

            return secondDump.isEmpty();
        }

        return source == null && target == null;
    }

//    @Override
//    public C objectsNotContainsInSecondCollection(C first, C second) {
//        C firstDump = constructor.get();
//        firstDump.addAll(first);
//
//        for (V secondValue : second) {
//            Iterator<V> iterator = firstDump.iterator();
//            while (iterator.hasNext()) {
//                V firstValue = iterator.next();
//                if (comparator.equal(firstValue, secondValue)) {
//                    iterator.remove();
//                    break;
//                }
//            }
//        }
//
//        return firstDump;
//    }

}
