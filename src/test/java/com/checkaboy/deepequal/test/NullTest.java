package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.comparator.collection.CollectionComparator;
import com.checkaboy.deepequal.comparator.field.FieldComparator;
import com.checkaboy.deepequal.comparator.field.builder.FieldComparatorBuilder;
import com.checkaboy.deepequal.comparator.object.ObjectComparator;
import com.checkaboy.deepequal.comparator.object.IObjectComparator;
import com.checkaboy.deepequal.comparator.collection.strategy.UnorderedCollectionComparisonStrategy;
import com.checkaboy.deepequal.model.book.dto.AuthorDto;
import com.checkaboy.deepequal.model.book.dto.BookDto;
import org.junit.Test;

/**
 * @author Taras Shaptala
 */
public class NullTest {

    @Test
    public void simpleNullTest() {
        IObjectComparator<AuthorDto, AuthorDto> authorComparator = new ObjectComparator<>();
        IObjectComparator<BookDto, BookDto> bookComparator = new ObjectComparator<>();

        authorComparator.put("books", new FieldComparator<>(
                AuthorDto::getBooks,
                AuthorDto::getBooks,
                new CollectionComparator<>(new UnorderedCollectionComparisonStrategy<>(), bookComparator)
        ));

        bookComparator.put("author", new FieldComparator<>(
                BookDto::getAuthor,
                BookDto::getAuthor,
                new ObjectComparator<>(authorComparator)
        ));

        authorComparator.put("id", FieldComparatorBuilder.oneObjectFieldComparator(AuthorDto::getId));
        authorComparator.put("firstName", FieldComparatorBuilder.oneObjectFieldComparator(AuthorDto::getFirstName));
        authorComparator.put("lastName", FieldComparatorBuilder.oneObjectFieldComparator(AuthorDto::getLastName));

        bookComparator.put("id", FieldComparatorBuilder.oneObjectFieldComparator(BookDto::getId));
        bookComparator.put("name", FieldComparatorBuilder.oneObjectFieldComparator(BookDto::getName));

        AuthorDto full = new AuthorDto(null, null, null, null);
        AuthorDto empty = null;

        System.out.println(authorComparator.compare(null, full, empty));
    }

}
