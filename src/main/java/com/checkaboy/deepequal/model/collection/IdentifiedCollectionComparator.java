package com.checkaboy.deepequal.model.collection;

import com.checkaboy.deepequal.factory.ICollectionFactory;
import com.checkaboy.deepequal.model.collection.interf.ICollectionComparator;
import com.checkaboy.deepequal.model.collection.interf.IIdentifiedCollectionComparator;
import com.checkaboy.deepequal.model.single.interf.IComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public abstract class IdentifiedCollectionComparator<C extends Collection<V>, V>
        extends CollectionComparator<C, V>
        implements IIdentifiedCollectionComparator<C, V> {

    private final IComparator<V> identifierComparator;

    public IdentifiedCollectionComparator(ICollectionFactory<C, V> collectionFactory, IComparator<V> comparator, IComparator<V> identifierComparator) {
        super(collectionFactory, comparator);
        this.identifierComparator = identifierComparator;
    }

    @Override
    public boolean equal(C first, C second) {
        if (first != null && second != null) {
            if (first.size() != second.size())
                return false;

            for (V firstValue : first) {
                List<V> find = findObjectsByIdentificationComparator(firstValue, second);
                if (find.isEmpty())
                    return false;
                else if (find.size() > 1)
                    throw new IllegalArgumentException("identifier comparator find more than 1 value, incorrect settings");
                else if (!comparator.equal(firstValue, find.get(0)))
                    return false;
            }

            return true;
        }

        return first == null && second == null;
    }

    public List<V> findObjectsByIdentificationComparator(V object, Collection<V> collection) {
        List<V> find = new ArrayList<>();

        for (V secondValue : collection) {
            if (identifierComparator.equal(object, secondValue))
                find.add(secondValue);
        }

        return find;
    }

    @Override
    public C objectsNotContainsInSecondList(C first, C second) {
        return null;
    }

    @Override
    public C objectsNotContainsInSecondListButEqualsByIdentifier(C first, C second) {
        return null;
    }

}
