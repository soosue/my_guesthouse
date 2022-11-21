package com.java.guesthouse.guesthouse.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointUseRepository extends JpaRepository<PointUse, Long> {
    Page<PointUse> findByMemberId(Long memberId, Pageable pageable);
}
