package com.example.demo.dto;

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
    private Integer releaseYear;
    private String description;
    private DirectorDto director;
}
