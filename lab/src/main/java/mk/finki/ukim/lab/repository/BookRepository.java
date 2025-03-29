package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByNameContainingOrAuthor_NameOrAuthor_Surname(String bookName, String authorName, String authorSurname);
}
