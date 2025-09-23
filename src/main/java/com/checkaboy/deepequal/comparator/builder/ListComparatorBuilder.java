package com.checkaboy.deepequal.comparator.builder;

import com.checkaboy.deepequal.comparator.builder.interf.ICollectionComparatorBuilder;
import com.checkaboy.deepequal.comparator.model.CollectionComparator;
import com.checkaboy.deepequal.comparator.model.IdentifiedCollectionComparator;
import com.checkaboy.deepequal.comparator.model.interf.ICollectionComparator;
import com.checkaboy.deepequal.comparator.model.interf.IFieldComparator;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.List;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class ListComparatorBuilder<SV, TV>
        extends AbstractTypifiedContainer<TV>
        implements ICollectionComparatorBuilder<List<SV>, SV, List<TV>, TV> {

    //    private Supplier<List<V>> constructor = ArrayList::new;
    private IFieldComparator<SV, TV> comparator = (comparisonContext, source, target) -> Objects.equals(source, target);
    private IFieldComparator<SV, TV> identifierComparator;

    public ListComparatorBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(targetType);
    }

//    @Override
//    public ListComparatorBuilder<V> setConstructor(Supplier<List<V>> constructor) {
//        this.constructor = constructor;
//        return this;
//    }

    @Override
    public ListComparatorBuilder<SV, TV> setComparator(IFieldComparator<SV, TV> comparator) {
        this.comparator = comparator;
        return this;
    }

    @Override
    public ListComparatorBuilder<SV, TV> setIdentifierComparator(IFieldComparator<SV, TV> identifierComparator) {
        this.identifierComparator = identifierComparator;
        return this;
    }

    public ICollectionComparator<List<SV>, SV, List<TV>, TV> build() {
        if (identifierComparator == null) return new CollectionComparator<>(/*constructor, */comparator);
        else return new IdentifiedCollectionComparator<>(comparator, identifierComparator);
    }

}
