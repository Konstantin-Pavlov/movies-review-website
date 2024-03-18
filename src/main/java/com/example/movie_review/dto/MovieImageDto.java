package com.example.movie_review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieImageDto {
    private MultipartFile file;

    //    @JsonProperty("movie_id")
    private long movieId;
}
