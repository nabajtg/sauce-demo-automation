package com.swaglab.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum LoginCredentials {

    STANDARD_USER("standard_user", "secret_sauce"),
    LOCKED_OUT_USER("locked_out_user", "secret_sauce"),
    PROBLEM_USER("problem_user", "secret_sauce"),
    PERFORMANCE_GLITCH_USER("performance_glitch_user", "secret_sauce"),
    ERROR_USER("error_user", "secret_sauce"),
    VISUAL_USER("visual_user", "secret_sauce"),
    INVALID_USER("invalid_user", "invalid_password");

    String username;
    String password;

    public static LoginCredentials get(String username){
        return Arrays.asList(LoginCredentials.values()).stream()
            .filter(value->value.getUsername().equals(username))
            .findFirst().get();
    }


}
