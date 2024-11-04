package com.game.dice.usermanagement.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class UserRequest {
    @NonNull
    @NotBlank
    @Email
    @Setter
    @Getter
    private String username;

    @NonNull
    @NotBlank
    @Setter
    @Getter
    private String password;

    @NonNull
    @NotBlank
    @Setter
    @Getter
    private String name;


    public UserRequest(final String username, final String fullName, final String password) {
        this.username = username;
        this.name = fullName;
        this.password = password;
    }
}
