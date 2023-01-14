package com.java.guesthouse.refactor.wishlist.ui;

import com.java.guesthouse.aop.HomeAspect;
import com.java.guesthouse.guestdelluna.service.DellunaService;
import com.java.guesthouse.refactor.wishlist.service.WishlistService;
import com.java.guesthouse.refactor.wishlist.service.dto.SaveWishlistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class WishlistController {
    private final DellunaService dellunaService;
    private final WishlistService wishlistService;

    @PostMapping("/v1/wishlists")
    public ResponseEntity<Void> saveWishlist(SaveWishlistRequest request, HttpSession session) {
        Long wishlistId = wishlistService.save(request, (Long) session.getAttribute("memberCode"));

        return ResponseEntity.created(URI.create("/v1/wishlists/" + wishlistId)).build();
    }
}
