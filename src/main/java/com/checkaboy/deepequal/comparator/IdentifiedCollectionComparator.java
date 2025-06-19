package com.checkaboy.deepequal.comparator;

import com.checkaboy.deepequal.comparator.interf.IFieldComparator;
import com.checkaboy.deepequal.comparator.interf.IIdentifiedCollectionComparator;
import com.checkaboy.deepequal.factory.ICollectionFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class IdentifiedCollectionComparator<C extends Collection<V>, V>
        extends CollectionComparator<C, V>
        implements IIdentifiedCollectionComparator<C, V> {

    private final IFieldComparator<V> identifierComparator;

    public IdentifiedCollectionComparator(ICollectionFactory<C, V> collectionFactory, IFieldComparator<V> comparator, IFieldComparator<V> identifierComparator) {
        super(collectionFactory, comparator);
        this.identifierComparator = identifierComparator;
    }

    @Override
    public boolean equal(C first, C second) {
        if (first != null && second != null) {
            if (first.size() != second.size())
                return false;

            for (V firstValue : first) {
                V find = findObjectsByIdentificationComparator(firstValue, second);
                if (!comparator.equal(firstValue, find))
                    return false;
            }

            return true;
        }

        return first == null && second == null;
    }

    @Override
    public C objectsNotContainsInSecondCollectionButEqualsByIdentifier(C first, C second) {
        C collection = collectionFactory.createNew();

        if (first != null && second != null) {
            for (V firstValue : first) {
                V find = findObjectsByIdentificationComparator(firstValue, second);
                if (!comparator.equal(firstValue, find)) {
                    collection.add(find);
                }
            }
        }

        return collection;
    }

    protected V findObjectsByIdentificationComparator(V object, Collection<V> collection) {
        List<V> find = new ArrayList<>();

        for (V secondValue : collection) {
            if (identifierComparator.equal(object, secondValue))
                find.add(secondValue);
        }

        if (find.size() == 1) return find.get(0);
        else throw new IllegalArgumentException("Identifier comparator found more than 1 object");
    }

}
