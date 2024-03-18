package com.example.movie_review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private long id;
    private String name;
    @JsonProperty("release_year") // wired to database
    private Integer releaseYear;
    private String description;
    private DirectorDto director;
}
