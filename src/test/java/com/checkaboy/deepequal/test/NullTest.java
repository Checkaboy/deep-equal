package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.comparator.model.CollectionComparator;
import com.checkaboy.deepequal.comparator.model.FieldComparator;
import com.checkaboy.deepequal.comparator.model.ObjectComparator;
import com.checkaboy.deepequal.comparator.model.interf.IObjectComparator;
import com.checkaboy.deepequal.comparator.strategy.collection.UnorderedCollectionComparisonStrategy;
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

        authorComparator.put("id", FieldComparator.oneObjectFieldComparator(AuthorDto::getId));
        authorComparator.put("firstName", FieldComparator.oneObjectFieldComparator(AuthorDto::getFirstName));
        authorComparator.put("lastName", FieldComparator.oneObjectFieldComparator(AuthorDto::getLastName));

        bookComparator.put("id", FieldComparator.oneObjectFieldComparator(BookDto::getId));
        bookComparator.put("name", FieldComparator.oneObjectFieldComparator(BookDto::getName));

        AuthorDto full = new AuthorDto(null, null, null, null);
        AuthorDto empty = null;

        System.out.println(authorComparator.compare(null, full, empty));
    }

}
