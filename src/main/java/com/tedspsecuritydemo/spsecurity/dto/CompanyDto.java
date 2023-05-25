package com.tedspsecuritydemo.spsecurity.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@Builder
public class CompanyDto {

    @NotBlank
    public String companyName;
    @NotBlank
    public String companyLocation;

}
