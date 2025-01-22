package com.senai.ThymeLeaf.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Setter
@Getter
@Component
@RequestScope
public class LoginSaveDto {

    private static String email;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        LoginSaveDto.email = email;
    }
}
