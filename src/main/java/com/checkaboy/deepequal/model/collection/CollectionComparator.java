package com.checkaboy.deepequal.model.collection;

import com.checkaboy.deepequal.factory.ICollectionFactory;
import com.checkaboy.deepequal.model.collection.interf.ICollectionComparator;
import com.checkaboy.deepequal.model.single.interf.IComparator;

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
    protected final IComparator<V> comparator;

    public CollectionComparator(ICollectionFactory<C, V> collectionFactory, IComparator<V> comparator) {
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
    public C objectsNotContainsInSecondList(C first, C second) {
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
