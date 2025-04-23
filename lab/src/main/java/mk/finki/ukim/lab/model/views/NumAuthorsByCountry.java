package mk.finki.ukim.lab.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("select * from public.num_authors_by_country")
@Immutable
public class NumAuthorsByCountry {
    @Id
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "num_authors")
    private Integer numAuthors;
}
