package com.benjamin.parsy.learningprojectgraphql.controller;

import com.benjamin.parsy.learningprojectgraphql.DataHelper;
import com.benjamin.parsy.learningprojectgraphql.entity.Author;
import com.benjamin.parsy.learningprojectgraphql.entity.Book;
import com.benjamin.parsy.learningprojectgraphql.service.AuthorService;
import com.benjamin.parsy.learningprojectgraphql.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@GraphQlTest
class BookControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @Test
    void recentBooks() {

        Author author1 = DataHelper.createAuthor("John", "Smith", true);
        Book book1 = DataHelper.createBook("title1", "text1", "category1", author1.getId(), true);

        Author author2 = DataHelper.createAuthor("Jane", "Doe", true);
        Book book2 = DataHelper.createBook("title2", "text2", "category2", author2.getId(), true);

        Mockito.when(bookService.getRecentBooks(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(List.of(book1, book2));

        Mockito.when(authorService.findAllByIdIn(List.of(author1.getId(), author2.getId())))
                .thenReturn(List.of(author1, author2));

        Map<String, Object> variables = Map.of("count", 10,
                "offset", 0);

        graphQlTester.documentName("book-test/recent-books")
                .variables(variables)
                .execute()
                .errors()
                .verify()
                .path("recentBooks")
                .entityList(Book.class)
                .hasSize(2);

    }

    @Test
    void createBook() {

        Author author1 = DataHelper.createAuthor("John", "Smith", true);

        Mockito.when(authorService.findById(author1.getId()))
                .thenReturn(Optional.of(author1));

        Mockito.when(authorService.findAllByIdIn(List.of(author1.getId())))
                .thenReturn(List.of(author1));

        Mockito.when(bookService.save(Mockito.any()))
                .thenAnswer(invocation -> {
                    Book savedEntity = invocation.getArgument(0);
                    savedEntity.setId(1L);
                    return savedEntity;
                });

        Map<String, Object> variables = Map.of("title", "testTitle1",
                "text", "testText1",
                "category", "testCategory1",
                "authorId", author1.getId());

        graphQlTester.documentName("book-test/create-book")
                .variables(variables)
                .execute()
                .errors()
                .verify()
                .path("createBook")
                .entity(Book.class);

    }

}