package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        var book1 = new Book("1234567891", "Norhtern Lights", "Lyra Silverstar", 9.90);
        var book2 = new Book("12345678912", "Plar Journey", "Iorek Polarson", 12.90);
        bookRepository.saveBook(book1);
        bookRepository.saveBook(book2);
    }
}
