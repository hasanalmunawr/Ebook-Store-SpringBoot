package com.hasanalmunawr.Ebook_Store.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EbookRepository extends JpaRepository<EbookEntity, Integer> {
}
