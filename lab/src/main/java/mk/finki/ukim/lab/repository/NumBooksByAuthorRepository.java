package mk.finki.ukim.lab.repository;

import org.springframework.transaction.annotation.Transactional;
import mk.finki.ukim.lab.model.views.NumBooksByAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface NumBooksByAuthorRepository extends JpaRepository<NumBooksByAuthor, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.num_books_by_author", nativeQuery = true)
    void refreshMaterializedView();


    Optional<NumBooksByAuthor> findByAuthorId(Long id);
}
