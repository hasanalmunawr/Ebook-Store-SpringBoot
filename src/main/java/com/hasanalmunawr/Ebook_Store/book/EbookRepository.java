package com.hasanalmunawr.Ebook_Store.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EbookRepository extends JpaRepository<EbookEntity, Integer> {

    Optional<EbookEntity> findByIsbn(String isbn);


    @Query("""
            SELECT b FROM EbookEntity AS b
            WHERE b.price <= :min AND b.price >= :max
            """)
    List<EbookEntity> findByPriceRange(double min, double max);


    @Query("""
            SELECT b FROM EbookEntity AS b
            ORDER BY b.price ASC
            """)
    List<EbookEntity> filterEbookByPriceCheaper();

    @Query("""
            SELECT b FROM EbookEntity AS b
            ORDER BY b.price DESC 
            """)
    List<EbookEntity> filterEbookByPriceExpensive();

    @Query("""
            SELECT b FROM EbookEntity AS b
            Where b.title LIKE %:title%
            """)
    List<EbookEntity> filterEbookByTitle(@Param("title") String title);

    @Query("""
            select b From EbookEntity as b
            Where b.author.firstName || b.author.lastName LIKE %:name%
            """)
    List<EbookEntity> filterEbookByAuthor(@Param("name") String name);
}
