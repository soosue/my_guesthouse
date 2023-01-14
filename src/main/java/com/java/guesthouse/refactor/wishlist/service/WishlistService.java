package com.java.guesthouse.refactor.wishlist.service;

import com.java.guesthouse.member.domain.Member;
import com.java.guesthouse.member.service.MemberService;
import com.java.guesthouse.refactor.wishlist.domain.Wishlist;
import com.java.guesthouse.refactor.wishlist.domain.WishlistRepository;
import com.java.guesthouse.refactor.wishlist.service.dto.SaveWishlistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final MemberService memberService;

    public Long save(SaveWishlistRequest request, Long memberId) {
        Member member = memberService.findById(memberId);

        Wishlist wishlist = new Wishlist(member, request.getHouseId());
        wishlistRepository.save(wishlist);

        return wishlist.getId();
    }
}
