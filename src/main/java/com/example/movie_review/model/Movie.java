package com.example.movie_review.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private long id;
    private String name;
    private Integer releaseYear;
    private String description;
    private Long directorId;
}
