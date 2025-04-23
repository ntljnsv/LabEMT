package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.model.domain.Author;
import mk.finki.ukim.lab.model.projections.AuthorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a")
    List<AuthorProjection> authorProjection();
}
