package com.checkaboy.deepequal.comparator;

import com.checkaboy.deepequal.comparator.interf.ICollectionComparator;
import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.factory.ICollectionFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class CollectionComparator<C extends Collection<V>, V>
        implements ICollectionComparator<C, V> {

    protected final ICollectionFactory<C, V> collectionFactory;
    protected final IFieldComparator<V> comparator;

    public CollectionComparator(ICollectionFactory<C, V> collectionFactory, IFieldComparator<V> comparator) {
        this.collectionFactory = collectionFactory;
        this.comparator = comparator;
    }

    @Override
    public boolean equal(C first, C second) {
        if (first != null && second != null) {
            if (first.size() != second.size())
                return false;

            List<V> secondDump = new ArrayList<>(second);

            for (V firstValue : first) {
                boolean matched = false;

                Iterator<V> iterator = secondDump.iterator();
                while (iterator.hasNext()) {
                    V secondValue = iterator.next();
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

        return first == null && second == null;
    }

    @Override
    public C objectsNotContainsInSecondCollection(C first, C second) {
        C firstDump = collectionFactory.createNew();
        firstDump.addAll(first);

        for (V secondValue : second) {
            Iterator<V> iterator = firstDump.iterator();
            while (iterator.hasNext()) {
                V firstValue = iterator.next();
                if (comparator.equal(firstValue, secondValue)) {
                    iterator.remove();
                    break;
                }
            }
        }

        return firstDump;
    }

}
