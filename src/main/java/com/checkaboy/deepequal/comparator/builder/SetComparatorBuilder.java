package com.checkaboy.deepequal.comparator.builder;

import com.checkaboy.deepequal.comparator.builder.interf.ICollectionComparatorBuilder;
import com.checkaboy.deepequal.comparator.model.CollectionComparator;
import com.checkaboy.deepequal.comparator.model.IdentifiedCollectionComparator;
import com.checkaboy.deepequal.comparator.model.interf.ICollectionComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public class SetComparatorBuilder<SV, TV>
        extends AbstractTypifiedContainer<TV>
        implements ICollectionComparatorBuilder<Set<SV>, SV, Set<TV>, TV> {

    //    private Supplier<Set<V>> constructor = HashSet::new;
    private IFieldComparator<SV, TV> comparator = Objects::equals;
    private IFieldComparator<SV, TV> identifierComparator;

    public SetComparatorBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(targetType);
    }

//    @Override
//    public SetComparatorBuilder<V> setConstructor(Supplier<Set<V>> constructor) {
//        this.constructor = constructor;
//        return this;
//    }

    @Override
    public SetComparatorBuilder<SV, TV> setComparator(IFieldComparator<SV, TV> comparator) {
        this.comparator = comparator;
        return this;
    }

    @Override
    public SetComparatorBuilder<SV, TV> setIdentifierComparator(IFieldComparator<SV, TV> identifierComparator) {
        this.identifierComparator = identifierComparator;
        return this;
    }

    public ICollectionComparator<List<SV>, SV, List<TV>, TV> build() {
        if (identifierComparator == null) return new CollectionComparator<>(/*constructor, */comparator);
        else return new IdentifiedCollectionComparator<>(comparator, identifierComparator);
    }

}
