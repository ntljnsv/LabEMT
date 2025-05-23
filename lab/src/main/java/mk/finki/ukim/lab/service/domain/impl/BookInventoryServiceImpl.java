package mk.finki.ukim.lab.service.domain.impl;

import mk.finki.ukim.lab.model.domain.Book;
import mk.finki.ukim.lab.model.domain.BookInventory;
import mk.finki.ukim.lab.model.exceptions.BookNotFoundException;
import mk.finki.ukim.lab.model.exceptions.NoAvailableCopiesException;
import mk.finki.ukim.lab.repository.BookInventoryRepository;
import mk.finki.ukim.lab.service.domain.BookInventoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookInventoryServiceImpl implements BookInventoryService {

    private final BookInventoryRepository bookInventoryRepository;

    public BookInventoryServiceImpl(BookInventoryRepository bookInventoryRepository) {
        this.bookInventoryRepository = bookInventoryRepository;
    }

    @Override
    public Optional<BookInventory> create(Book book, int availableCopies) {
        BookInventory bookInventory = new BookInventory(book, availableCopies);
        book.setInventory(bookInventory);
        return Optional.of(bookInventoryRepository.save(bookInventory));
    }

    @Override
    public Optional<BookInventory> updateByBookId(Long bookId, int availableCopies) {

        BookInventory inventory = bookInventoryRepository.findByBookId(bookId)
                .orElseThrow(() -> new NoAvailableCopiesException(bookId));
        inventory.setAvailableCopies(availableCopies);
        bookInventoryRepository.save(inventory);
        return Optional.of(bookInventoryRepository.save(inventory));
    }

    @Override
    public boolean deleteByBookId(Long bookId) {

        Optional<BookInventory> inventory = bookInventoryRepository.findByBookId(bookId);
        if(inventory.isPresent()) {
            bookInventoryRepository.delete(inventory.get());
            return true;
        }
        return false;
    }

    @Override
    public int getNumberOfCopiesByBookId(Long bookId) {

        BookInventory inventory = bookInventoryRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        return inventory.getAvailableCopies();
    }

    @Override
    public Optional<BookInventory> borrowBook(Long bookId) {

        BookInventory inventory = bookInventoryRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        if(inventory.getAvailableCopies() > 0) {
            inventory.setAvailableCopies(inventory.getAvailableCopies() - 1);
            bookInventoryRepository.save(inventory);
            return Optional.of(inventory);
        } else {
            throw new NoAvailableCopiesException(bookId);
        }
    }

    @Override
    public Optional<BookInventory> returnBook(Long bookId) {

        BookInventory inventory = bookInventoryRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        inventory.setAvailableCopies(inventory.getAvailableCopies() + 1);
        bookInventoryRepository.save(inventory);
        return Optional.of(inventory);
    }
}
