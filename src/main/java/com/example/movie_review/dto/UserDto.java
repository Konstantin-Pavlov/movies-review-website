package com.example.movie_review.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    //    private Integer id;
    @Email
    private String email;

    /*^:
    Asserts the start of the string.
    [a-zA-Z]+: Matches one or more alphabetical characters (both uppercase and lowercase).
    (?: ...): Non-capturing group.
    Matches a single space.
    [a-zA-Z]+: Matches one or more alphabetical characters (both uppercase and lowercase) after the space.
    ?: Makes the space and the characters after it optional.
    $: Asserts the end of the string.
    */
//    @NotBlank(message = "name can't be blank")
    @Pattern(
            regexp = "^[A-Z][a-zA-Z]*((\\s[A-Z][a-zA-Z]*)*)?$", message =
            "Example matches: Dave, John Doe, Li, O'Neil; " +
                    "Example non-matches: 123, dave, John Doe Smith, John123, Mary-Ann"
    )
    private String name;
    @Size(min = 4, max = 24, message = "Password length must be between 4 and 24 characters")
    private String password;
    private boolean enabled;
}
