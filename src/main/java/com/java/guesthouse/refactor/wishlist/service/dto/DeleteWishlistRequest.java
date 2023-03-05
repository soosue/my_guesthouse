package com.java.guesthouse.refactor.wishlist.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class DeleteWishlistRequest {
    @Schema(description = "게스트하우스id", required = true)
    private Long houseId;
}
