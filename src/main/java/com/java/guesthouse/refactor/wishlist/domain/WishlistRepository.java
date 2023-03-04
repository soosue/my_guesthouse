package com.java.guesthouse.refactor.wishlist.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    void deleteByMemberIdAndHouseId(Long memberId, Long houseId);
}
