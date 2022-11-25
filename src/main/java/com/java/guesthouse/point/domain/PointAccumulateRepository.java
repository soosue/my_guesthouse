package com.java.guesthouse.point.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointAccumulateRepository extends JpaRepository<PointAccumulate, Long> {
    long countByMemberId(Long memberCode);

    Page<PointAccumulate> findByMemberId(Long memberId, Pageable pageable);
}
