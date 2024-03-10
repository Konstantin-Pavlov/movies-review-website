package com.example.demo.model;

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
    private Integer year;
    private String description;
}
