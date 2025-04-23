package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.model.views.NumAuthorsByCountry;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NumAuthorsByCountryRepository extends JpaRepository<NumAuthorsByCountry, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.num_authors_by_country", nativeQuery = true)
    void refreshMaterializedView();

    Optional<NumAuthorsByCountry> findByCountryId(Long countryId);
}
