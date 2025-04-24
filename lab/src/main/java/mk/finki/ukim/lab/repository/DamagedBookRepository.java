package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.model.views.DamagedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface DamagedBookRepository extends JpaRepository<DamagedBook, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.damaged_books", nativeQuery = true)
    void refreshMaterializedView();

}
