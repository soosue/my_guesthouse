package com.java.guesthouse.guesthouse.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local, Long> {
    List<Local> findByNameLike(String localName);
}
