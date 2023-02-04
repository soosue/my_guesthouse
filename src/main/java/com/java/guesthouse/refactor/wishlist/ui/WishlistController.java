package com.java.guesthouse.refactor.wishlist.ui;

import com.java.guesthouse.refactor.wishlist.service.WishlistService;
import com.java.guesthouse.refactor.wishlist.service.dto.DeleteWishlistRequest;
import com.java.guesthouse.refactor.wishlist.service.dto.SaveWishlistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @PostMapping("/v1/wishlists")
    public ResponseEntity<Void> saveWishlist(SaveWishlistRequest request, HttpSession session) {
        Long wishlistId = wishlistService.save(request, getMemberId(session));

        return ResponseEntity.created(URI.create("/v1/wishlists/" + wishlistId)).build();
    }

    @DeleteMapping("/v1/wishlists")
    public ResponseEntity<Void> deleteWishlist(DeleteWishlistRequest request, HttpSession session) {
        wishlistService.delete(request, getMemberId(session));

        return ResponseEntity.ok().build();
    }

    private static Long getMemberId(HttpSession session) {
        return (Long) session.getAttribute("memberCode");
    }
}
