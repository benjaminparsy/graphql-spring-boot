package com.benjamin.parsy.learningprojectgraphql.controller;

import com.benjamin.parsy.learningprojectgraphql.entity.Author;
import com.benjamin.parsy.learningprojectgraphql.entity.Book;
import com.benjamin.parsy.learningprojectgraphql.service.AuthorService;
import com.benjamin.parsy.learningprojectgraphql.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @QueryMapping
    public List<Book> recentBooks(@Argument int count, @Argument int offset) {
        return bookService.getRecentBooks(count, offset);
    }

    @BatchMapping
    public Map<Book, Author> author(List<Book> bookList) {

        List<Long> authorIdList = bookList.stream()
                .map(Book::getAuthorId)
                .distinct()
                .toList();

        Map<Long, Author> authorById = authorService.findAllByIdIn(authorIdList)
                .stream()
                .collect(Collectors.toMap(Author::getId, Function.identity()));

        return bookList.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        book -> authorById.get(book.getAuthorId()), (a, b) -> b
                ));
    }

    @MutationMapping
    public Book createBook(@Argument String title,
                           @Argument String text,
                           @Argument String category,
                           @Argument Long authorId) throws NoSuchFieldException {

        Optional<Author> optionalAuthor = authorService.findById(authorId);

        if (optionalAuthor.isEmpty()) {
            throw new NoSuchFieldException(String.format("Author not found for the id '%s'", authorId));
        }

        Book book = new Book();
        book.setTitle(title);
        book.setText(text);
        book.setCategory(category);
        book.setCreatedDate(LocalDateTime.now());
        book.setAuthorId(authorId);

        bookService.save(book);

        return book;
    }

}