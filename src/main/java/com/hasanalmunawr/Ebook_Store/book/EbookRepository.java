package com.hasanalmunawr.Ebook_Store.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        WHERE b.price <= :max AND b.price >= :min
        """)
    Page<EbookEntity> findByPriceRange(double min, double max, Pageable pageable);



    @Query("""
            SELECT b FROM EbookEntity AS b
            ORDER BY b.price ASC
            """)
    Page<EbookEntity> filterEbookByPriceCheaper(Pageable pageable);

    @Query("""
            SELECT b FROM EbookEntity AS b
            ORDER BY b.price DESC 
            """)
    Page<EbookEntity> filterEbookByPriceExpensive(Pageable pageable);

    @Query("""
            SELECT b FROM EbookEntity AS b
            Where b.title LIKE %:title%
            """)
    Page<EbookEntity> filterEbookByTitle(@Param("title") String title, Pageable pageable);

    @Query("""
            select b From EbookEntity as b
            Where b.author.firstName || b.author.lastName LIKE %:name%
            """)
    Page<EbookEntity> filterEbookByAuthor(@Param("name") String name, Pageable pageable);

    @Query("""
  SELECT b FROM EbookEntity AS b
        WHERE b.price <= :max AND b.price >= :min
        ORDER BY b.price ASC
""")
    Page<EbookEntity> filterEbookAsc(double min, double max, Pageable pageable);

    @Query("""
      SELECT b FROM EbookEntity AS b
      WHERE b.price <= :max AND b.price >= :min
      ORDER BY b.price DESC 
    """)
    List<EbookEntity> filterEbookDsc(@Param("min") double min, @Param("max") double max);


    Page<EbookEntity> findByPriceBetween(double min, double max, Pageable pageable);

    Page<EbookEntity> findByPublisher(String publisher, Pageable pageable);
}
