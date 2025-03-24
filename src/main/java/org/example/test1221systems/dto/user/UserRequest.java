package org.example.test1221systems.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @Max(120)
    @Min(1)
    private Long age;

    @NotNull
    @Max(500)
    @Min(1)
    private Long weight;

    @NotNull
    @Max(300)
    @Min(1)
    private Long growth;

    @NotNull
    @NotBlank
    private String target;

}
