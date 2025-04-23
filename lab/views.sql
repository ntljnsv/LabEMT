create materialized view num_books_by_author as
    select a.id as author_id, count(b.id) as num_books
    from authors a
    left join books b on b.author_id = a.id
    group by a.id;

create materialized view num_authors_by_country as
    select c.id as country_id, count(a.id) as num_authors
    from countries c
    left join authors a on a.country_id = c.id
    group by c.id;