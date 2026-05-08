package com.checkaboy.deepequal.test;

import com.checkaboy.deepequal.comparator.IComparator;
import com.checkaboy.deepequal.comparator.v2.*;
import com.checkaboy.deepequal.model.book.dto.AuthorDto;
import com.checkaboy.deepequal.model.book.dto.BookDto;
import com.checkaboy.deepequal.model.book.entity.AuthorEntity;
import com.checkaboy.deepequal.model.book.entity.BookEntity;
import com.google.common.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

public class ComparatorV2ArchitectureTest {

    @Test
    public void shouldCompareDtoAndEntityWithCycleUsingRegistry() {
        DefaultComparatorRegistry registry = new DefaultComparatorRegistry();

        TypeToken<AuthorEntity> ae = TypeToken.of(AuthorEntity.class);
        TypeToken<AuthorDto> ad = TypeToken.of(AuthorDto.class);
        TypeToken<BookEntity> be = TypeToken.of(BookEntity.class);
        TypeToken<BookDto> bd = TypeToken.of(BookDto.class);

        new ObjectComparatorBuilder<>(ae, ad, registry)
                .field(
                        "id",
                        TypeToken.of(Long.class),
                        TypeToken.of(Long.class),
                        AuthorEntity::getId,
                        AuthorDto::getId,
                        (ctx, s, t) -> Objects.equals(s, t)
                )
                .field(
                        "firstName",
                        TypeToken.of(String.class),
                        TypeToken.of(String.class),
                        AuthorEntity::getFirstName,
                        AuthorDto::getFirstName,
                        (ctx, s, t) -> (
                                (ComparisonContext) ctx).profile() == ComparisonProfile.ID_ONLY || Objects.equals(s, t)
                )
                .field(
                        "lastName",
                        TypeToken.of(String.class),
                        TypeToken.of(String.class),
                        AuthorEntity::getLastName,
                        AuthorDto::getLastName,
                        (ctx, s, t) -> (
                                (ComparisonContext) ctx).profile() == ComparisonProfile.ID_ONLY || Objects.equals(s, t)
                )
                .field(
                        "books",
                        new TypeToken<>() {},
                        new TypeToken<>() {},
                        AuthorEntity::getBooks,
                        AuthorDto::getBooks,
                        new CollectionComparator<>(be, bd)
                )
                .buildAndRegister();

        new ObjectComparatorBuilder<>(be, bd, registry)
                .field("id",
                        TypeToken.of(Long.class),
                        TypeToken.of(Long.class),
                        BookEntity::getId,
                        BookDto::getId,
                        (ctx, s, t) -> Objects.equals(s, t))
                .field(
                        "name",
                        TypeToken.of(String.class),
                        TypeToken.of(String.class),
                        BookEntity::getName,
                        BookDto::getName,
                        (ctx, s, t) -> (
                                (ComparisonContext) ctx).profile() != ComparisonProfile.FULL || Objects.equals(s, t)
                )
                .field(
                        "author",
                        ae,
                        ad,
                        BookEntity::getAuthor,
                        BookDto::getAuthor
                )
                .buildAndRegister();

        AuthorEntity authorEntity = new AuthorEntity(1L, "John", "Steinbeck");
        BookEntity bookEntity = new BookEntity(10L, "East of Eden");
        authorEntity.setBooks(Arrays.asList(bookEntity));
        bookEntity.setAuthor(authorEntity);

        AuthorDto authorDto = new AuthorDto(1L, "John", "Steinbeck");
        BookDto bookDto = new BookDto(10L, "East of Eden");
        authorDto.setBooks(Arrays.asList(bookDto));
        bookDto.setAuthor(authorDto);

        ComparisonContext fullContext = new ComparisonContext(registry, ComparisonProfile.FULL);
        IComparator<AuthorEntity, AuthorDto> comparator = registry.resolve(ae, ad, fullContext);
        Assert.assertTrue(comparator.compare(fullContext, authorEntity, authorDto));

        authorDto.setFirstName("Jane");
        ComparisonContext idOnlyContext = new ComparisonContext(registry, ComparisonProfile.ID_ONLY);
        Assert.assertTrue(comparator.compare(idOnlyContext, authorEntity, authorDto));
    }

}
