package com.benjamin.parsy.learningprojectgraphql.service.impl;

import com.benjamin.parsy.learningprojectgraphql.entity.Book;
import com.benjamin.parsy.learningprojectgraphql.repository.BookRepository;
import com.benjamin.parsy.learningprojectgraphql.service.BookService;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BookServiceImpl extends GenericServiceImpl<Book> implements BookService {

    private final BookRepository repository;

    protected BookServiceImpl(BookRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Book> getRecentBooks(int count, int offset) {
        return repository.findAllByOrderByCreatedDate(count);
    }

    @Override
    public List<Book> findAllByAuthorIdIn(Collection<@NotNull Long> authorId) {
        return repository.findAllByAuthorIdIn(authorId);
    }

}