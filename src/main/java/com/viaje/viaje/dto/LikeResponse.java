package com.viaje.viaje.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LikeResponse {
    @JsonProperty("liked")
    private boolean liked;

    public LikeResponse(boolean liked) {
        this.liked = liked;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}