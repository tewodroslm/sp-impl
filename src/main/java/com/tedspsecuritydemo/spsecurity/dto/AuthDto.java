package com.tedspsecuritydemo.spsecurity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {

    @NotEmpty(message = "User name can not be empty.")
    private String username;

    @NotEmpty(message = "Password name can not be empty.")
//    @Pattern(regexp = "^A-Za-z0-9 ]*$", message = "Only alphanumeric and space are allowed")
    private String password;

}
