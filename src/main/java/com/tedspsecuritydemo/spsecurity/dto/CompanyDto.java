package com.tedspsecuritydemo.spsecurity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDto {

    public String companyName;
    public String companyLocation;

}
