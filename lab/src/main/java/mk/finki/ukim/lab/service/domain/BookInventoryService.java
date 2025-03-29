package mk.finki.ukim.lab.service.domain;

import mk.finki.ukim.lab.model.domain.Book;
import mk.finki.ukim.lab.model.domain.BookInventory;

import java.util.Optional;

public interface BookInventoryService {

    Optional<BookInventory> create(Book book, int availableCopies);
    Optional<BookInventory> updateByBookId(Long bookId, int availableCopies);
    boolean deleteByBookId(Long bookId);
    int getNumberOfCopiesByBookId(Long bookId);
    Optional<BookInventory> borrowBook(Long bookId);
    Optional<BookInventory> returnBook(Long bookId);
}
