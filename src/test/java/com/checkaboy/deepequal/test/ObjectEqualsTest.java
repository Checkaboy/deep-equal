package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.comparator.collection.CollectionComparator;
import com.checkaboy.deepequal.comparator.field.FieldComparator;
import com.checkaboy.deepequal.comparator.field.builder.FieldComparatorBuilder;
import com.checkaboy.deepequal.comparator.object.ObjectComparator;
import com.checkaboy.deepequal.comparator.object.IObjectComparator;
import com.checkaboy.deepequal.comparator.collection.strategy.UnorderedCollectionComparisonStrategy;
import com.checkaboy.deepequal.comparator.transaction.ComparisonTransaction;
import com.checkaboy.deepequal.comparator.transaction.IComparisonTransaction;
import com.checkaboy.deepequal.context.cache.ComparisonContext;
import com.checkaboy.deepequal.model.book.dto.AuthorDto;
import com.checkaboy.deepequal.model.book.dto.BookDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class ObjectEqualsTest {

    @Test
    public void test1() {
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

        AuthorDto authorDto1 = createAuthor();
        authorDto1.setBooks(createBooks());
        authorDto1.getBooks().forEach(bookDto -> bookDto.setAuthor(authorDto1));

        AuthorDto authorDto2 = createAuthor();
        authorDto2.setBooks(createBooks());
        authorDto2.getBooks().forEach(bookDto -> bookDto.setAuthor(authorDto2));

        IComparisonTransaction<AuthorDto, AuthorDto> transaction = new ComparisonTransaction<>(authorComparator, ComparisonContext::new);

        System.out.println(transaction.compare(authorDto1, authorDto2));
    }

    public AuthorDto createAuthor() {
        AuthorDto author = new AuthorDto();
        author.setId(1L);
        author.setFirstName("testFirstName");
        author.setLastName("testLastName");
        return author;
    }

    public List<BookDto> createBooks() {
        int bookCount = 3;
        List<BookDto> books = new ArrayList<>(bookCount);

        for (int i = 0; i < bookCount; i++) {
            BookDto book = new BookDto();
            book.setId((long) i);
            book.setName("test name " + i);
            books.add(book);
        }

        return books;
    }

}
